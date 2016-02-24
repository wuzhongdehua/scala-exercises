package stdlib

import org.scalatest._

/** manifests
  *
  */
object Manifests extends FlatSpec with Matchers with exercise.Section {


  /** classManifestsManifests
    *
    * Note: In Scala 2.10, scala.reflect.ClassManifests are deprecated, and it is planned to deprecate scala.reflect.Manifest in favor of TypeTags and ClassTags in an upcoming point release. Thus, it is advisable to migrate any Manifest-based APIs to use Tags.  When Java-the-language added generic types in version 1.5, Java-the-virtual-machine (JVM) did not. Generic types are a fiction of the compiler. They exist at compile time, but they are omitted from the generated bytecode and are therefore unavailable at run time. This phenomenon is known as erasure. Java's type erasure leads to some peculiar limitations. To deal with these limitations, Java programmers often find themselves passing around `java.lang.Class<T>` objects: concrete manifestations of the otherwise abstract concept that is the type `T`.
    *
    * Scala 2.8  formalized the ability to encode type information into implicit parameters. It does this through two mechanisms: Manifests and implicit type constraints. Manifests were added specifically to handle arrays. This was done because in Scala arrays are generic classes, however on JVM they are encoded differently(`Array[Int]`, `Array[Double]` as opposed to `int[]` and `double[]`).
    *
    * For example, the following code will not compile as the compiler does not have enough information to decide on the runtime type of the resulting array:
    *
    * {{{
    * def tryMe[T](array: Array[T]) = Array(array(0))
    * }}}
    *
    * However, we can use Manifest to correct that:
    *
    * {{{
    * def tryMe[T : Manifest](array: Array[T]) = Array(array(0))
    * }}}
    *
    * To use *Manifests*, simply add an implicit `scala.reflect.Manifest[T]` parameter to your method, like so:
    *
    * {{{
    * def name[T](implicit m: scala.reflect.Manifest[T]) = m.toString()
    * }}}
    *
    * This method prints the name of any Scala type. To use it, invoke the method with some type parameter:
    *
    * {{{
    * name[Int => Int] // returns "scala.Function1[Int, Int]"
    * }}}
    *
    * When using implicit parameters, you usually have to declare a implicit identifier with the same type as your method expects, in the same scope where the method is called. With *Manifests*, the compiler automatically injects the implicit parameter for you, as long as it has enough type information to generate a *Manifest*. Essentially, this is a way of carrying over type information available at compile time into objects available at run time.
    */
  def classManifestsManifests(res0: String) {
    def inspect[T](l: List[T])(implicit manifest: scala.reflect.Manifest[T]) = manifest.toString()
    val list = 1 :: 2 :: 3 :: 4 :: 5 :: Nil
    inspect(list) should be(res0)
  }

  /** attachedToClassesManifests
    *
    * Manifests can be attached to classes. *Manifests* have other meta-information about the type erased:
    */
  def attachedToClassesManifests(res0: String) {
    class Monkey
    class Barrel[T](implicit m: scala.reflect.Manifest[T]) {
      def +(t: T) = "1 %s has been added".format(m.runtimeClass.getSimpleName)
    }
    val monkeyBarrel = new Barrel[Monkey]
    (monkeyBarrel + new Monkey) should be(res0)
  }

}