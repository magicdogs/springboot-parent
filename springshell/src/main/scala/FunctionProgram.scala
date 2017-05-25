

/**
  * Created by magicdog on 2017/5/25.
  */
object FunctionProgram {


  def main(args: Array[String]): Unit = {
    println(apply(layout,10))

    println(((x :Int) => x * x))

    var s = () => {System.getProperty("user.dir")}



    println(s)
    println(strcat("hello ")("world"))

    val st = new Cat("sss",1)
    st.sayName()
  }


  def strcat(s1 :String)(s2 :String) = s1 + s2

  def apply(f : Int=>String, v :Int): String = f(v)

  def layout[A](x :A) = "[" + x.toString + "]"

}
