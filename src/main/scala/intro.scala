import scala.concurrent.Future

trait Terminal[C[_]] {

  def read: C[String]

  def write(t: String): C[Unit]
}

type Now[X] = X

object TerminalSync extends Terminal[Now] {
  override def read = ???

  override def write(t: String) = ???
}

object TerminalAsync extends Terminal[Future] {
  override def read: Future[String] = ???

  override def write(t: String): Future[Unit] = ???
}
