package groovy.stream

public class MapTests extends spock.lang.Specification {
  def "Simple Map"() {
    setup:
    def stream = Stream.from x:1..2, y:1..2

    when:
    def result = stream.collect()

    then:
    result == [ [ x:1, y:1 ], [ x:2, y:1 ], [ x:1, y:2 ], [ x:2, y:2 ] ]
  }

  def "Map with limits"() {
    setup:
    def stream = Stream.from x:1..2, y:1..2 where { x == y }

    when:
    def result = stream.collect()

    then:
    result == [ [ x:1, y:1 ], [ x:2, y:2 ] ]
  }

  def "Map with transformation"() {
    setup:
    def stream = Stream.from x:1..2, y:1..2 transform { x + y }

    when:
    def result = stream.collect()

    then:
    result == [ 2, 3, 3, 4 ]
  }
}