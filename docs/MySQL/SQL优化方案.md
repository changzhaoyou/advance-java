# SQL优化

标签（空格分隔）： MySQL

---

一、数据库设计方面<br />
&emsp;&emsp;1、查询优化，尽量避免全表查询，条件where及order by列上要有索引。<br />
&emsp;&emsp;2、条件查询禁止null值，作为查询条件，索引会失效，数据设计上，尽量使用空串或者必填。<br />
&emsp;&emsp;3、重复数据较多的字段，尽量避免索引，查询效率不高，增加硬盘成本。<br />
&emsp;&emsp;4、一张表索引字段不要过多，导致插入和修改成本。最好不要超过6个。<br />
&emsp;&emsp;5、尽可能避免索引字段上频繁更新，因为索引列的顺序就是表记录物理存储顺序，修改索引列需要调整物理存储顺序，需要耗费大量资源。<br />
&emsp;&emsp;6、能用数字类型就不要用字符类型，字符需要一个个比较，数字类型一次就够。<br />
&emsp;&emsp;7、如果varchar可以用char代替，尽量使用char，存储空间小，查询效率高。<br />
需要创建索引<br/>
&emsp;&emsp;![image.png-101.6kB][1]<br />
不适合建索引<br/>
&emsp;&emsp;![image.png-76.8kB][2]<br />
二、SQL语句方面<br/>
&emsp;&emsp;![image.png-132.5kB][3]<br />
&emsp;&emsp;1、避免在sql上使用！=或者<>操作符，导致索引失效，全表扫描。<br />
&emsp;&emsp;2、避免使用 or 操作符，导致全表扫描，索引失效，用union all 替代。<br />
&emsp;&emsp;3、 in 和 not in 也会导致全表扫描，索引失效，如果连续可以用between解决。<br />
&emsp;&emsp;4、尽量避免sql语句上做计算操作符和函数，也会导致全表扫描，索引失效。<br />
&emsp;&emsp;5、尽量用exists代替in查询。<br />
&emsp;&emsp;6、不用使用select * 查询，去掉不必要字段的查询。<br />
&emsp;&emsp;7、order by优化方案，尽量使用index方式排序，避免使用filesort方式排序，尽可能在索引列上排序操作，遵照索引建的最佳左前缀原则。<br />
&emsp;&emsp;a）、双路排序<br />
&emsp;&emsp;b）、单路排序<br />
&emsp;&emsp;![image.png-211.7kB][4]<br/>
8、Group by 排序优化：优化手段和order by相似，group by实质是先排序后分组，遵照索引最左前缀原则，尽量规避having使用。<br />
&emsp;&emsp;![image.png-187.8kB][5]<br/>
三、Explain执行计划分析（https://www.cnblogs.com/gomysql/p/3720123.html）<br />
&emsp;&emsp;![image.png-56.3kB][6]<br/>
<br/>1、id：从大到小执行顺序，null值顺序最后执行，表示SQL执行顺序。
<br/>2、select_type:<br/>
&emsp;&emsp;a. SIMPLE：查询中不包含子查询或者UNION<br/>
&emsp;&emsp;b. 查询中若包含任何复杂的子部分，最外层查询则被标记为：PRIMARY<br/>
&emsp;&emsp;c. 在SELECT或WHERE列表中包含了子查询，该子查询被标记为：SUBQUERY<br/>
&emsp;&emsp;d. 在FROM列表中包含的子查询被标记为：DERIVED（衍生）用来表示包含在from子句中的子查询的select，mysql会递归执行并将结果放到一个临时表中。服务器内部称为"派生表"，因为该临时表是从子查询中派生出来的<br/>
&emsp;&emsp;e. 若第二个SELECT出现在UNION之后，则被标记为UNION；若UNION包含在FROM子句的子查询中，外层SELECT将被标记为：DERIVED<br/>
&emsp;&emsp;f. 从UNION表获取结果的SELECT被标记为：UNION RESULT、SUBQUERY和UNION还可以被标记为DEPENDENT和UNCACHEABLE。<br/>
&emsp;&emsp;DEPENDENT意味着select依赖于外层查询中发现的数据。UNCACHEABLE意味着select中的某些 特性阻止结果被缓存于一个item_cache中。<br/>
3、type:<br/>
&emsp;&emsp;ALL, index,  range, ref, eq_ref, const, system, NULL
从左到右，性能从最差到最好 
<br/>&emsp;&emsp;a、ALL：mysql将遍历全表以找到匹配的行 <br />
&emsp;&emsp;b、index：index只遍历索引树 <br />
&emsp;&emsp;c、rang:索引范围扫描，对索引的扫描开始某一个点，返回匹配的行 <br />
&emsp;&emsp;d、ref：使用非唯一索引或者唯一索引的前缀扫描，返回匹配某个单独值的记录行 <br />
&emsp;&emsp;e、eq_ref:类似ref，区别就在于使用唯一索引，表示只有一条记录匹配 <br />
&emsp;&emsp;f、const、system：当MySQL对查询某部分进行优化，并转换为一个常量时，使用这些类型访问 <br />
&emsp;&emsp;g、NULL：MySQL在优化过程中分解语句，执行时甚至不用访问表或索引，例如从一个索引列里选取最小值可以通过单独索引查找完成
<br/>4. possible_keys <br />
&emsp;&emsp;指出MySQL能使用哪个索引在表中找到记录，查询涉及到的字段上若存在索引，则该索引将被列出，但不一定被查询使用
<br/>5. key <br />
&emsp;&emsp;显示MySQL在查询中实际使用的索引，若没有使用索引，显示为NULL<br/>
6、Filtered列 <br />
&emsp;&emsp;显针对表里符合某个条件（WHERE或者子查询）的记录数百分比所做的一个悲观的评估，预估查询出来的数据在整个表中所占的百分比。
<br/>7、Extra <br />
&emsp;&emsp;a. Using index:该值表示相应的select操作中使用了覆盖索引 <br />
&emsp;&emsp;b. Using where: <br />
&emsp;&emsp;表示mysql服务器将在存储引擎检索行后再进行过滤。许多where条件里涉及索引中的列，当（并且如果）它读取索引时，就能被存储引擎检验，因此不是所有带where字句的查询都会显示"Using where"。有时"Using where"的出现就是一个暗示：查询可受益与不同的索引。 <br />
&emsp;&emsp;c. Using temporary:表示MySQL需要使用临时表来存储结果集，常见于排序和分组查询
&emsp;&emsp;这个值表示使用了内部临时(基于内存的)表。一个查询可能用到多个临时表。有很多原因都会导致MySQL在执行查询期间创建临时表。两个常见的原因是在来自不同表的上使用了DISTINCT,或者使用了不同的ORDER BY和GROUP BY列。可以强制指定一个临时表使用基于磁盘的MyISAM存储引擎。这样做的原因主要有两个：<br />
&emsp;&emsp;&emsp;&emsp;1)内部临时表占用的空间超过min(tmp_table_size，max_heap_table_size)系统变量的限制<br/>
&emsp;&emsp;&emsp;&emsp;2)使用了TEXT/BLOB 列 <br />
&emsp;&emsp;d. Using filesort:MySQL中无法利用索引完成的排序操作称为“文件排序”（主要因为order by引起，最好使用组合索引解决，要清楚了解最左前缀匹配原则） <br />
&emsp;&emsp;e. Using join buffer: <br />
&emsp;&emsp;改值强调了在获取连接条件时没有使用索引，并且需要连接缓冲区来存储中间结果。如果出现了这个值，那应该注意，根据查询的具体情况可能需要添加索引来改进能 <br />
&emsp;&emsp;f. Impossible where <br />
&emsp;&emsp;这个值强调了where语句会导致没有符合条件的行。 <br />
&emsp;&emsp;h. Select tables optimized away <br />
&emsp;&emsp;这个值意味着仅通过使用索引，优化器可能仅从聚合函数结果中返回一行. <br />
&emsp;&emsp;I. Index merges <br />
&emsp;&emsp;当MySQL 决定要在一个给定的表上使用超过一个索引的时候，就会出现以下格式中的一个，详细说明使用的索引以及合并的类型。 <br />
&emsp;&emsp;Using sort_union(...) <br />
&emsp;&emsp;Using union(...) <br />
&emsp;&emsp;Using intersect(...) <br />
四、SQL优化方案 <br />
1、慢查询SQL开启和捕获。 <br />
2、explain+慢查询分析 <br />
&emsp;&emsp;a)、小表驱动大表：in和exists哪个更优，决定于是否是小表驱动大表。 <br/>
&emsp;&emsp;![image.png-123.7kB][7]<br/>
&emsp;&emsp;exists语法：将主查询的数据，放到子查询作为条件验证，根据验证结果（TRUE或者FALSE）来决定查询是否得以保留。<br/>
&emsp;&emsp;![image.png-123.5kB][8]<br/>
3、show profile （默认关闭，保持最近查询15次运行结果）<br/>
&emsp;&emsp;mysql提供可以用来分析当前会话中语句执行的资源消耗情况，包含：<br/>
&emsp;&emsp;![image.png-58.8kB][9]<br/>
4、SQL数据库服务器参数优化<br/>
&emsp;&emsp;![image.png-105.1kB][10]<br/>


  [1]: http://static.zybuluo.com/yzz19881016/elghhzfdagfb13gq62i6ryh4/image.png
  [2]: http://static.zybuluo.com/yzz19881016/xib1dq1ppoi1jp6oms1hh5i2/image.png
  [3]: http://static.zybuluo.com/yzz19881016/yr1m2k5kxmvvr5wy9fdvgr11/image.png
  [4]: http://static.zybuluo.com/yzz19881016/mq7fm7zy8wxuk5g4zdk06npk/image.png
  [5]: http://static.zybuluo.com/yzz19881016/xgnzudok5g9t5fyjn77dhf1k/image.png
  [6]: http://static.zybuluo.com/yzz19881016/2mhl1700ikp3ios8gu6hjozv/image.png
  [7]: http://static.zybuluo.com/yzz19881016/h0uod0979effemf295nod92c/image.png
  [8]: http://static.zybuluo.com/yzz19881016/5pympsdxxfrsto313nc5td14/image.png
  [9]: http://static.zybuluo.com/yzz19881016/e14l6b33ennrcy4s4bsrauvz/image.png
  [10]: http://static.zybuluo.com/yzz19881016/lel5n4ef8kaxr80d831bdgmd/image.png