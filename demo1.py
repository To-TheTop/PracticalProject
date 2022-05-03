import pymysql
    # 打开数据库连接
    db = pymysql.connect(host="localhost", user="root", password="root", db="mysql")
    # 使用cursor()方法获取操作游标
    cursor = db.cursor()
    # SQL 插入语句
    sql = "INSERT INTO commodity(name, origin, price) VALUES ('%s', '%s', '%f')" % (name, origin, price)
try:
    # 执行sql语句
    cursor.execute(sql)
    # 提交到数据库执行
    db.commit()
except:
    # 如果发生错误则回滚
    db.rollback()
# 关闭数据库连接
db.close()


