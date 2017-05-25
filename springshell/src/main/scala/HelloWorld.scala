import java.util.Properties

import kafka.consumer.ConsumerConfig
import kafka.utils.ZkUtils
import org.apache.kafka.common.security.JaasUtils
import org.springframework.shell.Bootstrap

object HelloWorld {
  def main(args: Array[String]): Unit = {
    val in = ClassLoader.getSystemResourceAsStream("./zk.properties")
    val prop = new Properties();
    prop.load(in)
    val config = new ConsumerConfig(prop)

    val zkUtils = ZkUtils(config.zkConnect, config.zkSessionTimeoutMs,
      config.zkConnectionTimeoutMs, JaasUtils.isZkSecurityEnabled())

    listPaths("/",zkUtils)
    Bootstrap.main(args)
  }

  def listPaths(path :String,zkUtils: ZkUtils): Unit ={
    val parent = zkUtils.getChildrenParentMayNotExist(path)
    if(parent.size == 0){
      println(path)
      zkUtils.readDataMaybeNull(path)._1.foreach( p =>{
        println(p)
      })
      return
    }
    parent.foreach( m => {
        var vpath = "";
        if(path.equals("/")){
          vpath = path + m
        }else{
          vpath = path + "/" + m
        }
        //println(vpath)
        listPaths(vpath,zkUtils)
      })

  }


  def scale(): Unit = {
    println("Hello, world!")
    val in = ClassLoader.getSystemResourceAsStream("./zk.properties")
    val prop = new Properties();
    prop.load(in)
    val config = new ConsumerConfig(prop)

    val zkUtils = ZkUtils(config.zkConnect, config.zkSessionTimeoutMs,
      config.zkConnectionTimeoutMs, JaasUtils.isZkSecurityEnabled())

    val topics = zkUtils.getAllTopics()
    println(zkUtils)
    topics.foreach( p=> {
      println("topics name : " + p)
      val pft = zkUtils.getPartitionsForTopics(Seq(p))
      val opt = pft.get(p)
      var i = 0;
      for ( i <- 0 to opt.size){
        println(i)
        val cg = zkUtils.getAllConsumerGroupsForTopic(p)
        cg.foreach( s => {
          println("\t consumer-group: " + s)
          val spath = zkUtils.getConsumerPartitionOwnerPath(s,p,i)
          println("\t \t " + spath)

          zkUtils.readDataMaybeNull(spath)._1.foreach( m => {
            println(m)
          })
        })
      }


    })
  }

}

