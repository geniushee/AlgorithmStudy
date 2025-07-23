import sys
from io import StringIO
from functools import wraps
from chicken_delivery import chicken_delivery
from drawingline import drawing_line
from number_set import number_set
from double_heap import *
from sns import tree_dp

def mock_input(input_data):
    def decorator(func):
        @wraps(func)
        def wrapper(*args, **kwargs):
            original_stdin = sys.stdin
            sys.stdin = StringIO(input_data)
            try:
                return func(*args, **kwargs)
            finally:
                sys.stdin = original_stdin
        return wrapper
    return decorator
            
@mock_input("""5 3
0 0 1 0 0
0 0 2 0 1
0 1 2 0 0
0 0 1 0 0
0 0 0 0 2""")
def test_chicken_delivery1():
    result = chicken_delivery()
    assert result == 5

@mock_input("""5 2
0 2 0 1 0
1 0 1 0 0
0 0 0 0 0
2 0 0 1 1
2 2 0 1 2""")
def test_chicken_delivery2():
    result = chicken_delivery()
    assert result == 10

@mock_input("""5 1
1 2 0 0 0
1 2 0 0 0
1 2 0 0 0
1 2 0 0 0
1 2 0 0 0""")
def test_chicken_delivery3():
    result = chicken_delivery()
    assert result == 11

@mock_input("""5 1
1 2 0 2 1
1 2 0 2 1
1 2 0 2 1
1 2 0 2 1
1 2 0 2 1""")
def test_chicken_delivery4():
    result = chicken_delivery()
    assert result == 32


@mock_input("""4
1 3
2 5
3 5
6 7""")
def test_drawing_line1():
    result = drawing_line()
    assert result == 5

@mock_input("""4
-1000000000 3
2 1000000000
3 5
6 7""")
def test_drawing_line2():
    result = drawing_line()
    assert result == 5

@mock_input("""7 8
0 1 3
1 1 7
0 7 6
1 7 1
0 3 7
0 4 2
0 1 1
1 1 3""")
def test_number_set1():
    result = number_set()
    assert result == 5

@mock_input("""2
7
I 16
I -5643
D -1
D 1
D 1
I 123
D -1
9
I -45
I 653
D 1
I -642
I 45
I 97
D 1
D -1
I 333""")
def test_double_heap1():
    result = double_heap()
    assert result == f"EMPTY\n333 -45"


@mock_input("""8
1 2
1 3
1 4
2 5
2 6
4 7
4 8""")
def test_sns1():
    result = tree_dp()
    assert result == 3

@mock_input("""9
1 2
1 3
2 4
3 5
3 6
4 7
4 8
4 9""")
def test_sns2():
    result = tree_dp()
    assert result == 2
