package groovy.stream

class ExtensionTests extends spock.lang.Specification {
  def 'closure test'() {
    setup:
      def stream = { -> 1 }.toStream()
    when:
      def result = stream.take( 4 ).collect()
    then:
      result == [ 1, 1, 1, 1 ]
  }

  def 'iterator test'() {
    setup:
      def iterator = [ hasNext:{ true }, next:{ 'a' } ] as Iterator
      def stream = iterator.toStream().map { it + 1 }
    when:
      def result = stream.take( 4 ).collect()
    then:
      result == [ 'a1', 'a1', 'a1', 'a1' ]
  }

  def 'iterable test'() {
    setup:
      def stream = ('a'..'z').toStream().filter { ((int)it[0]) % 2 == 1 }
    when:
      def result = stream.take( 4 ).collect()
    then:
      result == [ 'a', 'c', 'e', 'g' ]
  }

  def 'iterable test'() {
    setup:
      def stream = [ x:1..3, y:1..3 ].toStream().filter { x == y }
    when:
      def result = stream.take( 4 ).collect()
    then:
      result == [ [x:1,y:1], [x:2,y:2], [x:3,y:3] ]
  }

  def 'iterable test'() {
    setup:
      def stream = [ 1, 2, 3, 4, 5 ].toStream().map { it * x++ } using x:10
    when:
      def result = stream.take( 4 ).collect()
    then:
      result == [ 10, 22, 36, 52 ]
  }
}
