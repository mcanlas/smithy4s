/*
 *  Copyright 2021-2022 Disney Streaming
 *
 *  Licensed under the Tomorrow Open Source Technology License, Version 1.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     https://disneystreaming.github.io/TOST-1.0.txt
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package smithy4s
package http
package json

import _root_.smithy.api.JsonName
import com.github.plokhotnyuk.jsoniter_scala.core._
import smithy.api.HttpPayload
import smithy.api.TimestampFormat
import smithy.api.TimestampFormat._
import smithy4s.api.Untagged
import smithy4s.Document.DArray
import smithy4s.Document.DBoolean
import smithy4s.Document.DNull
import smithy4s.Document.DNumber
import smithy4s.Document.DObject
import smithy4s.Document.DString
import smithy4s.api.Discriminated
import smithy4s.internals.DiscriminatedUnionMember
import smithy4s.internals.Hinted
import smithy4s.internals.InputOutput
import smithy4s.schema._
import java.util
import java.util.UUID
import scala.collection.compat.immutable.ArraySeq
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{Map => MMap}
import scala.collection.immutable.VectorBuilder
import JCodec.JCodecMake

private[smithy4s] class SchematicJCodec(maxArity: Int) extends Schematic[JCodecMake] {
  private val emptyMetadata: MMap[String, Any] = MMap.empty

  def boolean: JCodecMake[Boolean] = Hinted.static {
    new JCodec[Boolean] {
      def expecting: String = "boolean"

      def decodeValue(cursor: Cursor, in: JsonReader): Boolean = in.readBoolean()

      def encodeValue(x: Boolean, out: JsonWriter): Unit = out.writeVal(x)

      def decodeKey(in: JsonReader): Boolean = in.readKeyAsBoolean()

      def encodeKey(x: Boolean, out: JsonWriter): Unit = out.writeKey(x)
    }
  }

  def char: JCodecMake[Char] = Hinted.static {
    new JCodec[Char] {
      def expecting: String = "char"

      def decodeValue(cursor: Cursor, in: JsonReader): Char = in.readChar()

      def encodeValue(x: Char, out: JsonWriter): Unit = out.writeVal(x)

      def decodeKey(in: JsonReader): Char = in.readKeyAsChar()

      def encodeKey(x: Char, out: JsonWriter): Unit = out.writeKey(x)
    }
  }

  def string: JCodecMake[String] = Hinted[JCodec].static {
    new JCodec[String] {
      def expecting: String = "string"

      def decodeValue(cursor: Cursor, in: JsonReader): String = in.readString(null)

      def encodeValue(x: String, out: JsonWriter): Unit = out.writeVal(x)

      def decodeKey(in: JsonReader): String = in.readKeyAsString()

      def encodeKey(x: String, out: JsonWriter): Unit = out.writeKey(x)
    }
  }

  def int: JCodecMake[Int] = Hinted[JCodec].static {
    new JCodec[Int] {
      def expecting: String = "int"

      def decodeValue(cursor: Cursor, in: JsonReader): Int = in.readInt()

      def encodeValue(x: Int, out: JsonWriter): Unit = out.writeVal(x)

      def decodeKey(in: JsonReader): Int = in.readKeyAsInt()

      def encodeKey(x: Int, out: JsonWriter): Unit = out.writeKey(x)
    }
  }

  def long: JCodecMake[Long] = Hinted[JCodec].static {
    new JCodec[Long] {
      def expecting: String = "long"

      def decodeValue(cursor: Cursor, in: JsonReader): Long = in.readLong()

      def encodeValue(x: Long, out: JsonWriter): Unit = out.writeVal(x)

      def decodeKey(in: JsonReader): Long = in.readKeyAsLong()

      def encodeKey(x: Long, out: JsonWriter): Unit = out.writeKey(x)
    }
  }

  def float: JCodecMake[Float] = Hinted[JCodec].static {
    new JCodec[Float] {
      def expecting: String = "float"

      def decodeValue(cursor: Cursor, in: JsonReader): Float = in.readFloat()

      def encodeValue(x: Float, out: JsonWriter): Unit = out.writeVal(x)

      def decodeKey(in: JsonReader): Float = in.readKeyAsFloat()

      def encodeKey(x: Float, out: JsonWriter): Unit = out.writeKey(x)
    }
  }

  def double: JCodecMake[Double] = Hinted[JCodec].static {
    new JCodec[Double] {
      def expecting: String = "double"

      def decodeValue(cursor: Cursor, in: JsonReader): Double = in.readDouble()

      def encodeValue(x: Double, out: JsonWriter): Unit = out.writeVal(x)

      def decodeKey(in: JsonReader): Double = in.readKeyAsDouble()

      def encodeKey(x: Double, out: JsonWriter): Unit = out.writeKey(x)
    }
  }

  def short: JCodecMake[Short] = Hinted[JCodec].static {
    new JCodec[Short] {
      def expecting: String = "short"

      def decodeValue(cursor: Cursor, in: JsonReader): Short = in.readShort()

      def encodeValue(x: Short, out: JsonWriter): Unit = out.writeVal(x)

      def decodeKey(in: JsonReader): Short = in.readKeyAsShort()

      def encodeKey(x: Short, out: JsonWriter): Unit = out.writeKey(x)
    }
  }

  def byte: JCodecMake[Byte] = Hinted[JCodec].static {
    new JCodec[Byte] {
      def expecting: String = "byte"

      def decodeValue(cursor: Cursor, in: JsonReader): Byte = in.readByte()

      def encodeValue(x: Byte, out: JsonWriter): Unit = out.writeVal(x)

      def decodeKey(in: JsonReader): Byte = in.readKeyAsByte()

      def encodeKey(x: Byte, out: JsonWriter): Unit = out.writeKey(x)
    }
  }

  def bytes: JCodecMake[ByteArray] = Hinted[JCodec].static {
    new JCodec[ByteArray] {
      def expecting: String = "byte-array"

      override def canBeKey: Boolean = false

      def decodeValue(cursor: Cursor, in: JsonReader): ByteArray = ByteArray(in.readBase64AsBytes(null))

      def encodeValue(x: ByteArray, out: JsonWriter): Unit = out.writeBase64Val(x.array, doPadding = true)

      def decodeKey(in: JsonReader): ByteArray = in.decodeError("Cannot use byte array as key")

      def encodeKey(x: ByteArray, out: JsonWriter): Unit = out.encodeError("Cannot use byte array as key")
    }
  }

  def bigdecimal: JCodecMake[BigDecimal] = Hinted[JCodec].static {
    new JCodec[BigDecimal] {
      def expecting: String = "big-decimal"

      def decodeValue(cursor: Cursor, in: JsonReader): BigDecimal = in.readBigDecimal(null)

      def decodeKey(in: JsonReader): BigDecimal = in.readKeyAsBigDecimal()

      def encodeValue(value: BigDecimal, out: JsonWriter): Unit = out.writeVal(value)

      def encodeKey(value: BigDecimal, out: JsonWriter): Unit = out.writeVal(value)
    }
  }

  def bigint: JCodecMake[BigInt] = Hinted[JCodec].static {
    new JCodec[BigInt] {
      def expecting: String = "big-int"

      def decodeValue(cursor: Cursor, in: JsonReader): BigInt = in.readBigInt(null)

      def decodeKey(in: JsonReader): BigInt = in.readKeyAsBigInt()

      def encodeValue(value: BigInt, out: JsonWriter): Unit = out.writeVal(value)

      def encodeKey(value: BigInt, out: JsonWriter): Unit = out.writeVal(value)
    }
  }

  def uuid: JCodecMake[UUID] = Hinted[JCodec].static {
    new JCodec[UUID] {
      def expecting: String = "uuid"

      def decodeValue(cursor: Cursor, in: JsonReader): UUID = in.readUUID(null)

      def encodeValue(x: UUID, out: JsonWriter): Unit = out.writeVal(x)

      def decodeKey(in: JsonReader): UUID = in.readKeyAsUUID()

      def encodeKey(x: UUID, out: JsonWriter): Unit = out.writeKey(x)
    }
  }

  def timestamp: JCodecMake[Timestamp] = Hinted[JCodec].from { hints =>
    new JCodec[Timestamp] {
      private[this] val format = hints.get(TimestampFormat).getOrElse(TimestampFormat.DATE_TIME)
      val expecting: String = Timestamp.showFormat(format)

      def decodeValue(cursor: Cursor, in: JsonReader): Timestamp =
        format match {
          case EPOCH_SECONDS => Timestamp.fromEpochSecond(in.readLong())
          case other =>
            Timestamp.parse(in.readString(null), other) match {
              case Some(value) => value
              case None        => in.decodeError("Wrong timestamp format")
            }
        }

      def encodeValue(x: Timestamp, out: JsonWriter): Unit =
        format match {
          case EPOCH_SECONDS => out.writeVal(x.epochSecond)
          case other         => out.writeVal(x.format(other))
        }

      def decodeKey(in: JsonReader): Timestamp = Timestamp.fromEpochSecond(in.readKeyAsLong())

      def encodeKey(x: Timestamp, out: JsonWriter): Unit = out.writeKey(x.epochSecond)
    }
  }

  def list[A](jc: JCodecMake[A]): JCodecMake[List[A]] = Hinted[JCodec].static {
    new JCodec[List[A]] {
      private[this] val a = jc.get

      def expecting: String = "list"

      override def canBeKey: Boolean = false

      def decodeValue(cursor: Cursor, in: JsonReader): List[A] =
        if (in.isNextToken('[')) {
          if (in.isNextToken(']')) Nil
          else {
            in.rollbackToken()
            val builder = new ListBuffer[A]
            var i = 0
            while ( {
              if (i >= maxArity)
                throw cursor.payloadError(
                  this,
                  s"input $expecting exceeded max arity of `$maxArity`"
                )
              builder += cursor.under(i)(cursor.decode(a, in))
              i += 1
              in.isNextToken(',')
            }) ()
            if (in.isCurrentToken(']')) builder.result()
            else in.arrayEndOrCommaError()
          }
        } else in.decodeError("Expected JSON array")

      def encodeValue(xs: List[A], out: JsonWriter): Unit = {
        out.writeArrayStart()
        var list = xs
        while (list ne Nil) {
          a.encodeValue(list.head, out)
          list = list.tail
        }
        out.writeArrayEnd()
      }

      def decodeKey(in: JsonReader): List[A] = in.decodeError("Cannot use vectors as keys")

      def encodeKey(xs: List[A], out: JsonWriter): Unit = out.encodeError("Cannot use vectors as keys")
    }
  }

  def set[A](jc: JCodecMake[A]): JCodecMake[Set[A]] = Hinted[JCodec].static {
    new JCodec[Set[A]] {
      private[this] val a = jc.get

      def expecting: String = "list"

      override def canBeKey: Boolean = false

      def decodeValue(cursor: Cursor, in: JsonReader): Set[A] =
        if (in.isNextToken('[')) {
          if (in.isNextToken(']')) Set.empty
          else {
            in.rollbackToken()
            val builder = Set.newBuilder[A]
            var i = 0
            while ({
              if (i >= maxArity)
                throw cursor.payloadError(
                  this,
                  s"input $expecting exceeded max arity of `$maxArity`"
                )
              builder += cursor.under(i)(cursor.decode(a, in))
              i += 1
              in.isNextToken(',')
            }) ()
            if (in.isCurrentToken(']')) builder.result()
            else in.arrayEndOrCommaError()
          }
        } else in.decodeError("Expected JSON array")

      def encodeValue(xs: Set[A], out: JsonWriter): Unit = {
        out.writeArrayStart()
        xs.foreach(x => a.encodeValue(x, out))
        out.writeArrayEnd()
      }

      def decodeKey(in: JsonReader): Set[A] = in.decodeError("Cannot use vectors as keys")

      def encodeKey(xs: Set[A], out: JsonWriter): Unit = out.encodeError("Cannot use vectors as keys")
    }
  }

  def map[K, V](jk: JCodecMake[K], jv: JCodecMake[V]): JCodecMake[Map[K, V]] =
    if (jk.get.canBeKey) objectMap(jk, jv)
    else arrayMap(jk, jv)

  private def objectMap[K, V](
      jk: JCodecMake[K],
      jv: JCodecMake[V]
  ): JCodecMake[Map[K, V]] = jk.productTransform(jv) { (k, v) =>
    new JCodec[Map[K, V]] {
      val expecting: String = "map"

      override def canBeKey: Boolean = false

      def decodeValue(cursor: Cursor, in: JsonReader): Map[K, V] =
        if (in.isNextToken('{')) {
          if (in.isNextToken('}')) Map.empty
          else {
            in.rollbackToken()
            val builder = Map.newBuilder[K, V]
            var i = 0
            while ({
              if (i >= maxArity)
                throw cursor.payloadError(
                  this,
                  s"input $expecting exceeded max arity of `$maxArity`"
                )
              builder += ((k.decodeKey(in), cursor.under(i)(cursor.decode(v, in))))
              i += 1
              in.isNextToken(',')
            }) ()
            if (in.isCurrentToken('}')) builder.result()
            else in.objectEndOrCommaError()
          }
        } else in.decodeError("Expected JSON object")

      def encodeValue(xs: Map[K, V], out: JsonWriter): Unit = {
        out.writeObjectStart()
        xs.foreach { case (key, value) =>
          k.encodeKey(key, out)
          v.encodeValue(value, out)
        }
        out.writeObjectEnd()
      }

      def decodeKey(in: JsonReader): Map[K, V] = in.decodeError("Cannot use maps as keys")

      def encodeKey(xs: Map[K, V], out: JsonWriter): Unit = out.encodeError("Cannot use maps as keys")
    }
  }

  private def arrayMap[K, V](
      k: JCodecMake[K],
      v: JCodecMake[V]
  ): JCodecMake[Map[K, V]] = {
    val kField = Field.required[JCodecMake, (K, V), K]("key", k, _._1)
    val vField = Field.required[JCodecMake, (K, V), V]("value", v, _._2)
    val kvCodec = struct(Vector(kField, vField))(vec => (vec(0).asInstanceOf[K], vec(1).asInstanceOf[V]))
    list(kvCodec).transform(_.biject(_.toMap, _.toList))
  }

  def bijection[A, B](
      a: JCodecMake[A],
      to: A => B,
      from: B => A
  ): JCodecMake[B] = a.transform(_.biject(to, from))

  def surjection[A, B](
      a: JCodecMake[A],
      to: Refinement[A, B],
      from: B => A
  ): JCodecMake[B] = a.xmap(to.asFunction, from)

  def suspend[A](a: Lazy[JCodecMake[A]]): JCodecMake[A] = Hinted.static {
    new JCodec[A] {
      lazy val underlying = a.value.get

      def expecting: String = underlying.expecting

      def decodeValue(cursor: Cursor, in: JsonReader): A = underlying.decodeValue(cursor, in)

      def encodeValue(x: A, out: JsonWriter): Unit = underlying.encodeValue(x, out)

      def decodeKey(in: JsonReader): A = underlying.decodeKey(in)

      def encodeKey(x: A, out: JsonWriter): Unit = underlying.encodeKey(x, out)
    }
  }

  private def taggedUnion[Z](
      first: Alt[JCodecMake, Z, _],
      rest: Vector[Alt[JCodecMake, Z, _]]
  )(total: Z => Alt.WithValue[JCodecMake, Z, _]): JCodec[Z] =
    new JCodec[Z] {
      val expecting: String = "tagged-union"

      override def canBeKey: Boolean = false

      def jsonLabel[A](alt: Alt[JCodecMake, Z, A]): String =
        alt.instance.hints.get(JsonName).map(_.value).getOrElse(alt.label)

      private[this] val handlerMap: util.HashMap[String, (Cursor, JsonReader) => Z] =
        new util.HashMap[String, (Cursor, JsonReader) => Z] {
          def handler[A](alt: Alt[JCodecMake, Z, A]) = {
            val codec = alt.instance.get
            (cursor: Cursor, reader: JsonReader) =>
              alt.inject(cursor.decode(codec, reader))
          }

          put(jsonLabel(first), handler(first))
          rest.foreach(alt => put(jsonLabel(alt), handler(alt)))
        }

      def decodeValue(cursor: Cursor, in: JsonReader): Z =
        if (in.isNextToken('{')) {
          if (in.isNextToken('}')) in.decodeError("Expected a single key/value pair")
          else {
            in.rollbackToken()
            val key = in.readKeyAsString()
            val result = cursor.under(key) {
              val handler = handlerMap.get(key)
              if (handler eq null) in.discriminatorValueError(key)
              handler(cursor, in)
            }
            if (in.isNextToken('}')) result
            else {
              in.rollbackToken()
              in.decodeError(s"Expected no other field after $key")
            }
          }
        } else in.decodeError("Expected JSON object")

      private[this] val altCache = new PolyFunction[Alt[JCodecMake, Z, *], JCodec] {
        def apply[A](fa: Alt[JCodecMake, Z, A]): JCodec[A] = fa.instance.get
      }.unsafeCache((first +: rest).map(alt => Existential.wrap(alt)))

      def encodeValue(z: Z, out: JsonWriter): Unit = {
        def writeValue[A](awv: Alt.WithValue[JCodecMake, Z, A]): Unit =
          altCache(awv.alt).encodeValue(awv.value, out)

        val awv = total(z)
        out.writeObjectStart()
        out.writeKey(jsonLabel(awv.alt))
        writeValue(awv)
        out.writeObjectEnd()
      }

      def decodeKey(in: JsonReader): Z = in.decodeError("Cannot use coproducts as keys")

      def encodeKey(x: Z, out: JsonWriter): Unit = out.encodeError("Cannot use coproducts as keys")
    }

  private def untaggedUnion[Z](
      first: Alt[JCodecMake, Z, _],
      rest: Vector[Alt[JCodecMake, Z, _]]
  )(total: Z => Alt.WithValue[JCodecMake, Z, _]): JCodec[Z] = new JCodec[Z] {
    def expecting: String = "untaggedUnion"

    override def canBeKey: Boolean = false

    private[this] val handlerList: Array[(Cursor, JsonReader) => Z] = {
      val res = Array.newBuilder[(Cursor, JsonReader) => Z]
      def handler[A](alt: Alt[JCodecMake, Z, A]) = {
        val codec = alt.instance.get
        (cursor: Cursor, reader: JsonReader) =>
          alt.inject(cursor.decode(codec, reader))
      }
      res += handler(first)
      rest.foreach(alt => res += handler(alt))
      res.result()
    }

    def decodeValue(cursor: Cursor, in: JsonReader): Z = {
      var z: Z = null.asInstanceOf[Z]
      val len = handlerList.length
      var i = 0
      while (z == null && i < len) {
        in.setMark()
        val handler = handlerList(i)
        try {
          z = handler(cursor, in)
        } catch {
          case _: Throwable =>
            in.rollbackToMark()
            i += 1
        }
      }
      if (z != null) z
      else cursor.payloadError(this, "Could not decode untagged union")
    }

    private[this] val altCache = new PolyFunction[Alt[JCodecMake, Z, *], JCodec] {
      def apply[A](fa: Alt[JCodecMake, Z, A]): JCodec[A] = fa.instance.get
    }.unsafeCache((first +: rest).map(alt => Existential.wrap(alt)))

    def encodeValue(z: Z, out: JsonWriter): Unit = {
      def writeValue[A](awv: Alt.WithValue[JCodecMake, Z, A]): Unit =
        altCache(awv.alt).encodeValue(awv.value, out)

      val awv = total(z)
      writeValue(awv)
    }

    def decodeKey(in: JsonReader): Z = in.decodeError("Cannot use coproducts as keys")

    def encodeKey(x: Z, out: JsonWriter): Unit = out.encodeError("Cannot use coproducts as keys")
  }

  private def discriminatedUnion[Z](
      first: Alt[JCodecMake, Z, _],
      rest: Vector[Alt[JCodecMake, Z, _]],
      discriminated: Discriminated
  )(total: Z => Alt.WithValue[JCodecMake, Z, _]): JCodec[Z] =
    new JCodec[Z] {
      def expecting: String = "discriminated-union"

      override def canBeKey: Boolean = false

      def jsonLabel[A](alt: Alt[JCodecMake, Z, A]): String =
        alt.instance.hints.get(JsonName).map(_.value).getOrElse(alt.label)

      private[this] val handlerMap: util.HashMap[String, (Cursor, JsonReader) => Z] =
        new util.HashMap[String, (Cursor, JsonReader) => Z] {
          def handler[A](alt: Alt[JCodecMake, Z, A]): (Cursor, JsonReader) => Z = {
            val codec = alt.instance.get
            (cursor: Cursor, reader: JsonReader) =>
              alt.inject(cursor.decode(codec, reader))
          }

          put(jsonLabel(first), handler(first))
          rest.foreach(alt => put(jsonLabel(alt), handler(alt)))
        }

      def decodeValue(cursor: Cursor, in: JsonReader): Z =
        if (in.isNextToken('{')) {
          in.setMark()
          if (in.skipToKey(discriminated.value)) {
            val key = in.readString("")
            in.rollbackToMark()
            in.rollbackToken()
            cursor.under(key) {
              val handler = handlerMap.get(key)
              if (handler eq null) in.discriminatorValueError(key)
              handler(cursor, in)
            }
          } else in.decodeError(s"Unable to find discriminator ${discriminated.value}")
        } else in.decodeError("Expected JSON object")

      private[this] val altCache = new PolyFunction[Alt[JCodecMake, Z, *], JCodec] {
        def apply[A](fa: Alt[JCodecMake, Z, A]): JCodec[A] = {
          val label = jsonLabel(fa)
          fa.instance.addHints(Hints(DiscriminatedUnionMember(discriminated.value, label))).get
        }
      }.unsafeCache((first +: rest).map(alt => Existential.wrap(alt)))

      def encodeValue(z: Z, out: JsonWriter): Unit = {
        def writeValue[A](awv: Alt.WithValue[JCodecMake, Z, A]): Unit =
          altCache(awv.alt).encodeValue(awv.value, out)

        val awv = total(z)
        writeValue(awv)
      }

      def decodeKey(in: JsonReader): Z = in.decodeError("Cannot use coproducts as keys")

      def encodeKey(x: Z, out: JsonWriter): Unit = out.encodeError("Cannot use coproducts as keys")
    }

  def union[Z](
      first: Alt[JCodecMake, Z, _],
      rest: Vector[Alt[JCodecMake, Z, _]]
  )(total: Z => Alt.WithValue[JCodecMake, Z, _]): JCodecMake[Z] = {
    Hinted[JCodec].from {
      case Untagged.hint(_) => untaggedUnion(first, rest)(total)
      case Discriminated.hint(d) => discriminatedUnion(first, rest, d)(total)
      case _ => taggedUnion(first, rest)(total)
    }
  }

  def enumeration[A](
      to: A => (String, Int),
      fromName: Map[String, A],
      fromOrdinal: Map[Int, A]
  ): JCodecMake[A] = Hinted.static {
    new JCodec[A] {
      val expecting: String = s"enumeration: [${fromName.keys.mkString(", ")}]"

      def decodeValue(cursor: Cursor, in: JsonReader): A = {
        val str = in.readString(null)
        fromName.get(str) match {
          case Some(value) => value
          case None        => in.enumValueError(str)
        }
      }

      def encodeValue(x: A, out: JsonWriter): Unit = out.writeVal(to(x)._1)

      def decodeKey(in: JsonReader): A = {
        val str = in.readKeyAsString()
        fromName.get(str) match {
          case Some(value) => value
          case None        => in.enumValueError(str)
        }
      }

      def encodeKey(x: A, out: JsonWriter): Unit = out.writeKey(to(x)._1)
    }
  }

  private def jsonLabel[A, Z](field: Field[JCodecMake, Z, A]): String =
    field.instance.hints
      .get(JsonName)
      .map(_.value)
      .getOrElse(field.label)

  private type Handler = (Cursor, JsonReader, util.HashMap[String, Any]) => Unit

  private def fieldHandler[Z, A](
      field: Field[JCodecMake, Z, A]
  ): Handler = {
    val codec = field.instance.get
    val label = field.label
    if (field.isRequired) { (cursor, in, mmap) =>
      val _ = mmap.put(label, cursor.under(label)(cursor.decode(codec, in)))
    } else { (cursor, in, mmap) =>
      cursor.under[Unit](label) {
        if (in.isNextToken('n')) in.readNullOrError[Unit]((), "Expected null")
        else {
          in.rollbackToken()
          val _ = mmap.put(label, cursor.decode(codec, in))
        }
      }
    }
  }

  private def fieldEncoder[Z, A](
      field: Field[JCodecMake, Z, A]
  ): (Z, JsonWriter) => Unit = {
    field.fold(new Field.Folder[JCodecMake, Z, (Z, JsonWriter) => Unit] {
      def onRequired[AA](
          label: String,
          instance: JCodecMake[AA],
          get: Z => AA
      ): (Z, JsonWriter) => Unit = {
        val codec = instance.get
        val jLabel = jsonLabel(field)
        if (jLabel.forall(JsonWriter.isNonEscapedAscii)) {
          (z: Z, out: JsonWriter) => {
            out.writeNonEscapedAsciiKey(jLabel)
            codec.encodeValue(get(z), out)
          }
        } else {
          (z: Z, out: JsonWriter) => {
            out.writeKey(jLabel)
            codec.encodeValue(get(z), out)
          }
        }
      }

      def onOptional[AA](
          label: String,
          instance: JCodecMake[AA],
          get: Z => Option[AA]
      ): (Z, JsonWriter) => Unit = {
        val codec = instance.get
        val jLabel = jsonLabel(field)
        if (jLabel.forall(JsonWriter.isNonEscapedAscii)) {
          (z: Z, out: JsonWriter) => {
            get(z) match {
              case Some(aa) =>
                out.writeNonEscapedAsciiKey(jLabel)
                codec.encodeValue(aa, out)
              case _ =>
            }
          }
        } else {
          (z: Z, out: JsonWriter) => {
            get(z) match {
              case Some(aa) =>
                out.writeKey(jLabel)
                codec.encodeValue(aa, out)
              case _ =>
            }
          }
        }
      }
    })
  }

  private type Fields[Z] = Vector[Field[JCodecMake, Z, _]]

  private def nonPayloadStruct[Z](
      fields: Fields[Z],
      maybeInputOutput: Option[InputOutput]
  )(
      const: Vector[Any] => Z,
      encode: (Z, JsonWriter, Vector[(Z, JsonWriter) => Unit]) => Unit
  ): JCodec[Z] =
    new JCodec[Z] {
      private[this] val documentFields =
        fields.filter { field =>
          val hints = field.instance.hints
          HttpBinding.fromHints(field.label, hints, maybeInputOutput).isEmpty
        }

      private[this] val handlers = new util.HashMap[String, Handler](documentFields.length) {
        documentFields.foreach(field => put(jsonLabel(field), fieldHandler(field)))
      }

      private[this] val documentEncoders = documentFields.map(field => fieldEncoder(field))

      def expecting: String = "object"

      override def canBeKey = false

      def decodeValue(cursor: Cursor, in: JsonReader): Z = decodeValue_(cursor, in)(emptyMetadata)

      override def decodeMessage(in: JsonReader): scala.collection.Map[String, Any] => Z =
        Cursor.withCursor(expecting)(decodeValue_(_, in))

      private def decodeValue_(
          cursor: Cursor,
          in: JsonReader
      ): scala.collection.Map[String, Any] => Z = {
        val buffer = new util.HashMap[String, Any](handlers.size)
        if (in.isNextToken('{')) {
          // In this case, metadata and payload are mixed together
          // and values field values must be sought from either.
          if (!in.isNextToken('}')) {
            in.rollbackToken()
            while ({
              val handler = handlers.get(in.readKeyAsString())
              if (handler eq null) in.skip()
              else handler(cursor, in, buffer)
              in.isNextToken(',')
            }) ()
            if (!in.isCurrentToken('}')) in.objectEndOrCommaError()
          }
        } else in.decodeError("Expected JSON object")

        // At this point, we have parsed the json and retrieved
        // all the values that interest us for the construction
        // of our domain object.
        // We therefore reconcile the values pulled from the json
        // with the ones pull the metadata, and call the constructor
        // on it.
        { (meta: scala.collection.Map[String, Any]) =>
          meta.foreach(kv => buffer.put(kv._1, kv._2))
          val stage2 = new VectorBuilder[Any]
          fields.foreach(f => stage2 += {
            val value = buffer.get(f.label)
            if (f.isRequired) {
              if (value == null) cursor.requiredFieldError(f.label, f.label)
              value
            } else Option(value)
          })
          const(stage2.result())
        }
      }

      def encodeValue(z: Z, out: JsonWriter): Unit = encode(z, out, documentEncoders)

      def decodeKey(in: JsonReader): Z = in.decodeError("Cannot use products as keys")

      def encodeKey(x: Z, out: JsonWriter): Unit = out.encodeError("Cannot use products as keys")
    }

  def payloadStruct[A, Z](
      payloadField: Field[JCodecMake, Z, _],
      fields: Fields[Z]
  )(codec: JCodec[payloadField.T], const: Vector[Any] => Z): JCodec[Z] =
    new JCodec[Z] {
      def expecting: String = "object"

      override def canBeKey = false

      def decodeValue(cursor: Cursor, in: JsonReader): Z = decodeValue_(cursor, in)(emptyMetadata)

      override def decodeMessage(in: JsonReader): scala.collection.Map[String, Any] => Z =
        Cursor.withCursor(expecting)(decodeValue_(_, in))

      private def decodeValue_(
          cursor: Cursor,
          in: JsonReader
      ): scala.collection.Map[String, Any] => Z = {
        val buffer = new util.HashMap[String, Any](1)
        // In this case, one field assumes the whole payload. We use
        // its associated codec.
        buffer.put(payloadField.label, cursor.decode(codec, in))

        // At this point, we have parsed the json and retrieved
        // all the values that interest us for the construction
        // of our domain object.
        // We therefore reconcile the values pulled from the json
        // with the ones pull the metadata, and call the constructor
        // on it.
        { (meta: scala.collection.Map[String, Any]) =>
          meta.foreach(kv => buffer.put(kv._1, kv._2))
          val stage2 = new VectorBuilder[Any]
          fields.foreach(f => stage2 += {
            val value = buffer.get(f.label)
            if (f.isRequired) {
              if (value == null) cursor.requiredFieldError(f.label, f.label)
              value
            } else Option(value)
          })
          const(stage2.result())
        }
      }

      def encodeValue(z: Z, out: JsonWriter): Unit = payloadField.foreachT(z)(codec.encodeValue(_, out))

      def decodeKey(in: JsonReader): Z = in.decodeError("Cannot use products as keys")

      def encodeKey(x: Z, out: JsonWriter): Unit = out.encodeError("Cannot use products as keys")
    }

  def struct[Z](
      fields: Vector[Field[JCodecMake, Z, _]]
  )(const: Vector[Any] => Z): JCodecMake[Z] =
    Hinted[JCodec].onHintsOpt[InputOutput, DiscriminatedUnionMember, Z] {
      case (maybeInputOutput, maybeDiscriminated) =>
        fields.find(_.instance.hints.get(HttpPayload).isDefined) match {
          case Some(payloadField) =>
            val codec = payloadField.instance.get
            payloadStruct(payloadField, fields)(codec, const)
          case None =>
            maybeDiscriminated match {
              case Some(d) =>
                val encode = {
                  (z: Z, out: JsonWriter, documentEncoders: Vector[(Z, JsonWriter) => Unit]) =>
                    out.writeObjectStart()
                    out.writeKey(d.propertyName)
                    out.writeVal(d.alternativeLabel)
                    documentEncoders.foreach(encoder => encoder(z, out))
                    out.writeObjectEnd()
                }
                nonPayloadStruct(fields, maybeInputOutput)(const, encode)
              case None =>
                val encode = {
                  (z: Z, out: JsonWriter, documentEncoders: Vector[(Z, JsonWriter) => Unit]) =>
                    out.writeObjectStart()
                    documentEncoders.foreach(encoder => encoder(z, out))
                    out.writeObjectEnd()
                }
                nonPayloadStruct(fields, maybeInputOutput)(const, encode)
            }
        }
    }

  def withHints[A](fa: JCodecMake[A], hints: Hints): JCodecMake[A] = fa.addHints(hints)

  def unit: JCodecMake[Unit] = Hinted.static {
    new JCodec[Unit] {
      def expecting: String = "empty object"

      override def canBeKey: Boolean = false

      def decodeValue(cursor: Cursor, in: JsonReader): Unit =
        if (!in.isNextToken('{') || !in.isNextToken('}')) in.decodeError("Expected empty object")

      def encodeValue(x: Unit, out: JsonWriter): Unit = {
        out.writeObjectStart()
        out.writeObjectEnd()
      }

      def decodeKey(in: JsonReader): Unit = in.decodeError("Cannot use Unit as keys")

      def encodeKey(x: Unit, out: JsonWriter): Unit = out.encodeError("Cannot use Unit as keys")
    }
  }

  def document: JCodecMake[Document] = Hinted.static {
    new JCodec[Document] {
      override def canBeKey: Boolean = false

      def encodeValue(doc: Document, out: JsonWriter): Unit = doc match {
        case s: DString  => out.writeVal(s.value)
        case b: DBoolean => out.writeVal(b.value)
        case n: DNumber  => out.writeVal(n.value)
        case a: DArray =>
          out.writeArrayStart()
          a.value.foreach(encodeValue(_, out))
          out.writeArrayEnd()
        case o: DObject =>
          out.writeObjectStart()
          o.value.foreach { kv =>
            out.writeKey(kv._1)
            encodeValue(kv._2, out)
          }
          out.writeObjectEnd()
        case _ => out.writeNull()
      }

      def decodeKey(in: JsonReader): Document = in.decodeError("Cannot use JSON document as keys")

      def encodeKey(x: Document, out: JsonWriter): Unit = out.encodeError("Cannot use JSON documents as keys")

      def expecting: String = "JSON document"

      // Borrowed from: https://github.com/plokhotnyuk/jsoniter-scala/blob/e80d51019b39efacff9e695de97dce0c23ae9135/jsoniter-scala-benchmark/src/main/scala/io/circe/CirceJsoniter.scala
      def decodeValue(cursor: Cursor, in: JsonReader): Document = {
        val b = in.nextToken()
        if (b == '"') {
          in.rollbackToken()
          new DString(in.readString(null))
        } else if (b == 'f' || b == 't') {
          in.rollbackToken()
          new DBoolean(in.readBoolean())
        } else if ((b >= '0' && b <= '9') || b == '-') {
          in.rollbackToken()
          new DNumber(in.readBigDecimal(null))
        } else if (b == '[') {
          new DArray({
            if (in.isNextToken(']')) IndexedSeq.empty[Document]
            else {
              in.rollbackToken()
              var arr = new Array[Document](4)
              var i = 0
              while ({
                if (i >= maxArity) maxArityError(cursor)
                if (i == arr.length) arr = java.util.Arrays.copyOf(arr, i << 1)
                arr(i) = decodeValue(in, null)
                i += 1
                in.isNextToken(',')
              }) {}
              if (in.isCurrentToken(']')) ArraySeq.unsafeWrapArray {
                if (i == arr.length) arr
                else java.util.Arrays.copyOf(arr, i)
              } else in.arrayEndOrCommaError()
            }
          })
        } else if (b == '{') {
          new DObject({
            if (in.isNextToken('}')) Map.empty
            else {
              in.rollbackToken()
              // We use the maxArity limit to mitigate DoS vulnerability in default Scala `Map` implementation: https://github.com/scala/bug/issues/11203
              val obj = Map.newBuilder[String, Document]
              var i = 0
              while ({
                if (i >= maxArity) maxArityError(cursor)
                obj += ((in.readKeyAsString(), decodeValue(in, null)))
                i += 1
                in.isNextToken(',')
              }) {}
              if (in.isCurrentToken('}')) obj.result()
              else in.objectEndOrCommaError()
            }
          })
        } else in.readNullOrError(DNull, "expected JSON document")
      }

      private def maxArityError(cursor: Cursor): Nothing =
        throw cursor.payloadError(this, s"input $expecting exceeded max arity of `$maxArity`")
    }
  }
}
