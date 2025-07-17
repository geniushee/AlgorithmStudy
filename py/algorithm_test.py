import sys
from io import StringIO
from functools import wraps
from chicken_delivery import chicken_delivery

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