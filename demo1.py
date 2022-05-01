import pymysql
    # 打开数据库连接
    db = pymysql.connect(host="localhost", user="root", password="root", db="mysql")
    # 使用cursor()方法获取操作游标
    cursor = db.cursor()
    # SQL 插入语句
    sql = "INSERT INTO commodity(name, origin, price) VALUES ('%s', '%s', '%f')" % (name, origin, price)


