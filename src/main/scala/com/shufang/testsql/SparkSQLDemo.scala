package com.shufang.testsql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.util.Properties

object SparkSQLDemo {

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir","E:\\winutils-master\\hadoop-2.8.3")
    val spark = SparkSession.builder().master("local[*]").appName("sparksql1").getOrCreate()

    val properties = new Properties()

    val in = SparkSQLDemo.getClass.getClassLoader.getResourceAsStream("jdbc.properties")
    properties.load(in)

    val url = properties.getProperty("url")
    val username: String = properties.getProperty("username")
    val password: String = properties.getProperty("password")
    val driver: String = properties.getProperty("driver")
    val dbtable: String = properties.getProperty("dbtable")
    Class.forName(driver)



//    val df: DataFrame = spark.read.format("jdbc")
//      .option("url", url)
//      .option("dbtable", dbtable)
//      .option("user", username)
//      .option("password", password)
//      .load()
//
//    df.show()

    import spark.implicits._
    val value: RDD[String] = spark.sparkContext.textFile("jdbc.properties")




    while (true){

    }

  }
}
