import org.scalatest.{FlatSpec, Matchers}
import Data._
import ciapplication.logic.WorldView

class WorldViewSpec extends FlatSpec with Matchers {

  "Bussiness Logic" should "generate an initial world view" in {
    val mutable = new Mutable(needsAgents)
    import mutable._
    program.initial shouldBe needsAgents


    it should "remove change nodes from pending" in {
      val world = WorldView(0, 0, managed, Map(node1 -> time3), Map.empty, time3)
      val mutable = new Mutable(world)
      import mutable._

      val old = world.copy(alive = Map.empty, pending = Map(node1 -> time2),
        time = time2)
      program.update(old) shouldBe world
    }


    it should "request agents when needed" in {
      val mutable = new Mutable(needsAgents)
      import mutable._

      val expected = needsAgents.copy(
        pending = Map(node1 -> time1)
      )

      program.act(needsAgents) shouldBe 0
      program.act(needsAgents) shouldBe 1

    }

  }

}
