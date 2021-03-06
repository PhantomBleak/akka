/*
 * Copyright (C) 2019 Lightbend Inc. <https://www.lightbend.com>
 */

package akka.actor

//import java.util.Queue
//import java.util.concurrent.ConcurrentLinkedQueue

//import akka.dispatch.{ UnboundedControlAwareMessageQueueSemantics, _ }
//import com.typesafe.config.Config

//MyNote
//the code here is pretty simple and straightforward
//use this.init() for hard coding the initiation.

private[akka] class Automata {
  var transitions: Vector[MyTransition] = Vector.empty[MyTransition]
  var lastTransitions: Vector[Int] = Vector.empty[Int]

  def addTransition(newTransition: MyTransition): Unit =
  {
    transitions = transitions :+ newTransition
  }

  def addLastTransition(lastTran: Int): Unit =
  {
    lastTransitions = lastTransitions :+ lastTran
  }

  def findPre(inputTransitions: Vector[MyTransition]): Vector[MyTransition] =
  {
    var returnVar: Vector[MyTransition] = Vector.empty[MyTransition]
    for (inputTransition ← inputTransitions)
      for (transition ← transitions) {
        if (transition.to == inputTransition.from)
          returnVar = returnVar :+ transition
      }
    returnVar
  }

  def singleFindPre(inputTransition: MyTransition): Vector[MyTransition] =
  {
    var returnVar: Vector[MyTransition] = Vector.empty[MyTransition]
      for (transition ← transitions) {
        if (transition.to == inputTransition.from)
          returnVar = returnVar :+ transition
      }
    returnVar
  }
  def findTransitionByMessageBundle(messageBundle: MessageBundle): Vector[MyTransition] = //returns vector[Transition] of messageBundles, null in case of not found
  {
    var returnVar: Vector[MyTransition] = Vector.empty[MyTransition]
    for (transition ← transitions)
      if (transition.messageBundle == messageBundle)
        returnVar = returnVar :+ transition
    returnVar
  }

  def isLastTransition(inputTransition: MyTransition): Boolean = //returns true if this is one of the lsat transitions, false otherwise
  {
    if (lastTransitions.isEmpty)
      return false
    if (lastTransitions.contains(inputTransition.to))
      return true
    return false
  }

  def init(): Unit =
  {
    //MyNote
    //use addTransition and addLastTransition to initiate the transitions according to algorithm
  }

}

   class MessageBundle(sender: ActorRef, message: Any, receiver: ActorRef) {
     val s=sender
     val m=message
     val r=receiver
   }

private[akka] case class MyTransition(from: Int, to: Int, messageBundle: MessageBundle, regTransition: Boolean) {

  //MyNote
  //if true, this is regular transition, else, negative transition

}

//MyNote
//TODO shayad bayad ye chizi be in traite ezafe konim, agi kar nakard
//TODO is "private[akka]" right????
//TODO you probably should test it again, so much changes since the last time this was tested.