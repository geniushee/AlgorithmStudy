"""문제
다항식(polynomial)은 문자의 거듭제곱의 상수 배들의 합을 표현하는 수식이다. 예를 들어 x2+2x+3 같은 식을 의미한다. 그중 변수가 하나인 것을 일변수 다항식이라고 하고 이는 다음과 같이 표현한다.

f(x) = axn + bxn-1+...+cx+d

최대 일차 일변수 다항식이 주어졌을 때 그 함수를 적분한 결과를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 최대 일차 일변수 다항식이 주어진다. 항의 개수는 최대 2개이고, 변수는 항상 x로 주어지며, 각 항은 공백 문자로 구분되지 않는다. 주어지는 계수는 절댓값이 10,000을 넘지 않는 0이 아닌 2의 배수이고 주어지는 상수는 절댓값이 10,000을 넘지 않는 정수이다. 차수가 같은 항은 한 번만 주어진다. 단, 계수의 절댓값이 1인 경우에는 1을 생략한다. 다항식은 차수가 큰 것부터 작아지는 순서대로 주어진다.

출력
주어진 일변수 다항식을 적분한 결과를 입력 형식과 동일하게 출력한다. 적분상수는 "W"로 x2은 "xx"로 표현한다.

예제 입력 1 
6x+6
예제 출력 1 
3xx+6x+W
"""
def parse(element, x_idx):
    op = element[0]
    si = 0
    x = "x"
    div = 1
    if op == "-":
        si = 1
    elif op == "+":
        si = 1
    else:
        op = ""

    if x_idx != -1:
        num = int(element[si:x_idx])
        x += "x"
        div = len(element) - x_idx + 1
        
    else:
        num = int(element[si:])
    
    num //= div
    if num == 1:
        num = ""
    return op + str(num) + x

def integration():
    text = input()
    x_idx = text.find("x")
    a,b = "", ""
    if x_idx != -1:
        a = parse(text[:x_idx+1],x_idx)

    if len(text[x_idx+1]) > 0:
        b = parse(text[x_idx+1:], -1)
    
    print(f"{a}{b}+W")

    return a + b + "+W"
