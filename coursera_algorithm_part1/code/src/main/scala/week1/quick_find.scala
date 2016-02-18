import Array._

object QuickFind {
  private var list_len = 10;
  private var ids = range(0, list_len);

  def connected(p: Int, q: Int): Boolean = {
    return ids(p) == ids(q);
  }

  def union(p: Int, q: Int) {
    var p_id = ids(p);
    var q_id = ids(q);

    var i = 0;
    for ( i <- 0 until list_len ) {
      if ( ids(i) == p_id ) {
        ids(i) = q_id;
      }
    }
  }

  def main(args: Array[String]): Unit = {
    //var union_cmds = Array(
    //  Array(4, 3),
    //  Array(3, 8),
    //  Array(6, 5),
    //  Array(9, 4),
    //  Array(2, 1)
    //)

    var cmd_str = "7-1 2-1 9-6 2-4 8-3 4-0"

    for ( cmd_pair <- cmd_str.split(" ") ) {
      var cmds = cmd_pair.split("-")
      union(cmds(0).toInt, cmds(1).toInt);
    }
    println(ids.mkString("<", " ", ">"));
  }
}
