import java.time.Instant
import java.time.Instant.parse

import Data._
import ciapplication.algebra.{Drone, MachineNode, Machines}
import ciapplication.logic.{DynAgents, WorldView}
import scalaz._
import Scalaz._

object Data {
  val node1 = MachineNode("123d1af-828f-4ba3-9fc0-a19d86852b5a")
  val node2 = MachineNode("550c4943-229e-47bf0-b6be-3d688c5f013f")
  val managed = NonEmptyList(node1, node2)

  import Instant.parse

  val time1 = parse("2017-03-03T18:07:00.000+01:00[Europe/London]")
  val time2 = parse("2017-03-03T18:59:00.000+01:00[Europe/London]") // +52 mins
  val time3 = parse("2017-03-03T19:06:00.000+01:00[Europe/London]") // +59 mins
  val time4 = parse("2017-03-03T23:07:00.000+01:00[Europe/London]") // +5 hours

  val needsAgents = WorldView(5, 0, managed, Map.empty, Map.empty, time1)
}

class Mutable(state: WorldView) {
  import Data._
  var started, stopped: Int = 0

  implicit val drone: Drone[Id] = new Drone[Id] {

    override def getBacklog: Int = state.backlog

    override def getAgents: Int = state.agents
  }

  implicit val machines: Machines[Id] = new Machines[Id]{
      def getAlive: Map[MachineNode, Instant] = state.alive
      def getManaged: NonEmptyList[MachineNode] = state.managed

    def getTime: Instant = state.time
    def start(node: MachineNode): MachineNode= {started += 1; node}
    def stop(node:MachineNode):MachineNode = {stopped += 1; node}
  }

  val program = new DynAgents[Id]
}
