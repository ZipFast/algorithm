class BSTNode(object):
    def __init__(self, key):
        self.key = key 
        self.right = None
        self.left = None
        self.parent = None 
    
class BST(object):
    def __init__(self):
        self.root = None 
    
    def insert(self, t):
        new = BSTNode(t) 
        if self.root is None:
            self.root = new 
            node = self.root 
        else:
            node = self.root
            while True:
                if t < node.key:
                    #Go left 
                    if node.left is None:
                        node.left = new 
                        new.parent = node 
                        break 
                    node = node.left
                else:
                    #Go right 
                    if node.right is None:
                        node.right = new 
                        new.parent = node 
                        break 
                    node = node.right 
        return node  
    
    def find(self, t):
        node = self.root 
        if node.key == t:
            return node 
        while node is not None:
            if t < node.key:
                node = node.left
            else:
                node = node.right 
        return None 
    
    def delete_min(self):
        """Delete the minimum key (and return the old node containing it)."""
        node = self.root 
        if node is None:
            return 
        # move to the leftmost  
        while node.left is not None:
            node = node.left
        # promote the node's right subtree 
        if node.parent is not None:
            node.parent.left = node.right 
        # if node's parent is None, the root is the smallest element 
        else: 
            self.root = node.right 
        if node.right is not None:
            node.right.parent = node.parent 
        parent = node.parent 
        node.parent = None 
        node.left = None 
        node.right = None 
        return node, parent 

    def __str__(self):
        if self.root is None: return '<empty tree>'
        def recurse(node):
            if node is None: return [], 0, 0
            label = str(node.key)
            left_lines, left_pos, left_width = recurse(node.left)
            right_lines, right_pos, right_width = recurse(node.right)
            middle = max(right_pos + left_width - left_pos + 1, len(label), 2)
            pos = left_pos + middle // 2
            width = left_pos + middle + right_width - right_pos
            while len(left_lines) < len(right_lines):
                left_lines.append(' ' * left_width)
            while len(right_lines) < len(left_lines):
                right_lines.append(' ' * right_width)
            if (middle - len(label)) % 2 == 1 and node.parent is not None and \
               node is node.parent.left and len(label) < middle:
                label += '.'
            label = label.center(middle, '.')
            if label[0] == '.': label = ' ' + label[1:]
            if label[-1] == '.': label = label[:-1] + ' '
            lines = [' ' * left_pos + label + ' ' * (right_width - right_pos),
                     ' ' * left_pos + '/' + ' ' * (middle-2) +
                     '\\' + ' ' * (right_width - right_pos)] + \
              [left_line + ' ' * (width - left_width - right_width) +
               right_line
               for left_line, right_line in zip(left_lines, right_lines)]
            return lines, pos, width
        return '\n'.join(recurse(self.root) [0])

def test():
    bst = BST()
    for i in range(10):
        bst.insert(i)
    bst.delete_min()
    print(bst)

if __name__ == "__main__":
    test()
