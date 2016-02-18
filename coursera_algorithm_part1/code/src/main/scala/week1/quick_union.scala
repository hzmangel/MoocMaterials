import Array._

object QuickUnion {
  private var list_len = 10;
  private var ids = range(0, list_len);

  def connected(p: Int, q: Int): Boolean = {
    return true
  }

  def root(idx: Int): Int = {
    var root_val = ids(idx);

    while (ids(root_val) != root_val) {
      root_val = ids(root_val);
    }
    return root_val;
  }

  def union(p: Int, q: Int) {
    var p_root = root(p);
    var q_root = root(q);

    if (p_root == q_root) {
      return
    }

    ids(p_root) = q_root;
  }

   def main(args: Array[String]): Unit = {
    // Verify course commands
    //var union_cmds = Array(
    //  Array(4,3),
    //  Array(3,8),
    //  Array(6,5),
    //  Array(9,4),
    //  Array(2,1),
    //  Array(5,0),
    //  Array(7,2),
    //  Array(6,1),
    //  Array(7,3)
    //);

    var cmd_str = "0-6 7-2 5-2 8-9 6-8 5-4 2-1 2-3 3-0"

    for ( cmd_pair <- cmd_str.split(" ") ) {
      var cmds = cmd_pair.split("-")
      union(cmds(0).toInt, cmds(1).toInt);
    }

    println(ids.mkString("<", " ", ">"));
  }
}
