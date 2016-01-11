#!/usr/bin/python2.7

from __future__ import print_function

import numpy
import sys
import itertools
import numpy as np
from collections import defaultdict
from scipy import sparse
from operator import itemgetter


class PagerankCalculator:

    def __init__(self, data_file, beta, epsilon=1e-10):
        self.data_file = data_file
        self.beta = beta
        self.epsilon = epsilon
        self.node_id_map = {}

    def build_transition_matrix(self):
        row = []
        col = []
        data = []

        # Convert node name (long name) to index, reduce memory usage. While the
        # node_idx is used to record current idx of nodes.
        node_idx = 0

        # How many values in one column, which is used to normalize transition
        # propbability of column
        col_cnt = defaultdict(int)

        with open(self.data_file) as fp:
            for l in fp:
                if (len(l.strip()) == 0) or (l.strip()[0] == '#'):
                    continue

                nodes = l.strip().split()

                if len(nodes) < 1:
                    raise Exception("Data line error, please check the file")

                src = nodes[0]
                node_idx = self._create_node_map(src, node_idx)

                for dest in nodes[1:]:
                    node_idx = self._create_node_map(dest, node_idx)

                    # Only add nodes with edge to the array, which will be
                    # used while generating sparse matrix.
                    row.append(self.node_id_map[dest])
                    col.append(self.node_id_map[src])
                    col_cnt[self.node_id_map[src]] += 1

        # Save dim of matrix
        self.dim = node_idx
        self.node_cnt = node_idx
        self.edge_cnt = len(row)

        for pt in col:
            # Sum of column should be 1
            data.append(1.0/col_cnt[pt])

        self.transit_ma = sparse.csc_matrix((data, (row, col)), shape=(self.dim, self.dim))

    def calculate(self):
        r0 = np.array([1.0/self.dim]).repeat(self.dim).reshape(self.dim, 1)
        B = (1 - self.beta) * np.ones((self.dim, 1)) / self.dim

        self.calc_round = 0
        r_prev = r0

        while True:
            self.calc_round += 1
            r_curr = self.beta * self.transit_ma.dot(r_prev) + B

            if np.linalg.norm(r_curr - r_prev) < self.epsilon:
                break
            else:
                r_prev = r_curr

        self.rslt = r_curr

    def print_result(self):
        self.build_transition_matrix()
        self.calculate()

        node_list = sorted(self.node_id_map.items(), key=itemgetter(1))
        rslt_array = [(node_list[idx][0].strip(), elem) for idx, elem in enumerate(self.rslt)]
        rslt_array.sort(key=itemgetter(1))
        rslt_array.reverse()

        vector_sum = sum((r[1] for r in rslt_array))

        print("Number of edges: ", self.edge_cnt)
        print("Number of nodes: ", self.node_cnt)
        print("Random jump factor: ", 1-self.beta)
        print("Iterations: ", self.calc_round)
        print("Sum of vector: ", vector_sum)
        for rslt in rslt_array:
            print("%.10e\t%10s" % (rslt[1] / vector_sum, rslt[0]))

    def _create_node_map(self, node, node_idx):
        if node not in self.node_id_map:
            self.node_id_map[node] = node_idx
            node_idx += 1

        return node_idx


def main():
    try:
        beta = float(sys.argv[2])
    except (IndexError, ValueError):
        beta = 0.8

    pr_calc = PagerankCalculator(sys.argv[1], beta)
    pr_calc.print_result()

if __name__ == '__main__':
    main()
