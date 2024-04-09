import unittest
from tree_utils import Node, reverse_tree


class TestReverseTree(unittest.TestCase):
    def test_reverse_single_node(self):
        root = Node(1)
        reversed_root = reverse_tree(root)
        self.assertEqual(reversed_root.val, 1)
        self.assertIsNone(reversed_root.left)
        self.assertIsNone(reversed_root.right)

    def test_reverse_multiple_nodes(self):
        root = Node(1, Node(2), Node(3, Node(4), Node(5)))
        reversed_root = reverse_tree(root)
        self.assertEqual(reversed_root.val, 1)
        self.assertEqual(reversed_root.left.val, 3)
        self.assertEqual(reversed_root.right.val, 2)
        self.assertEqual(reversed_root.left.left.val, 5)
        self.assertEqual(reversed_root.left.right.val, 4)
        self.assertIsNone(reversed_root.right.left)
        self.assertIsNone(reversed_root.right.right)
        self.assertIsNone(reversed_root.left.left.left)
        self.assertIsNone(reversed_root.left.left.right)
        self.assertIsNone(reversed_root.left.right.left)
        self.assertIsNone(reversed_root.left.right.right)

    def test_reverse_empty_tree(self):
        root = None
        reversed_root = reverse_tree(root)
        self.assertIsNone(reversed_root)


if __name__ == '__main__':
    unittest.main()
