class Node:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


def reverse_tree(root):
    if not root:
        return None

    root.left, root.right = reverse_subtree(root.right), reverse_subtree(root.left)
    return root


def reverse_subtree(node):
    if not node:
        return None

    node.left, node.right = node.right, node.left
    reverse_subtree(node.left)
    reverse_subtree(node.right)
    return node
