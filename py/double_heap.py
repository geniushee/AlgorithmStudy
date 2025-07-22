"""문제
이중 우선순위 큐(dual priority queue)는 전형적인 우선순위 큐처럼 데이터를 삽입, 삭제할 수 있는 자료 구조이다. 전형적인 큐와의 차이점은 데이터를 삭제할 때 연산(operation) 명령에 따라 우선순위가 가장 높은 데이터 또는 가장 낮은 데이터 중 하나를 삭제하는 점이다. 이중 우선순위 큐를 위해선 두 가지 연산이 사용되는데, 하나는 데이터를 삽입하는 연산이고 다른 하나는 데이터를 삭제하는 연산이다. 데이터를 삭제하는 연산은 또 두 가지로 구분되는데 하나는 우선순위가 가장 높은 것을 삭제하기 위한 것이고 다른 하나는 우선순위가 가장 낮은 것을 삭제하기 위한 것이다.

정수만 저장하는 이중 우선순위 큐 Q가 있다고 가정하자. Q에 저장된 각 정수의 값 자체를 우선순위라고 간주하자.

Q에 적용될 일련의 연산이 주어질 때 이를 처리한 후 최종적으로 Q에 저장된 데이터 중 최댓값과 최솟값을 출력하는 프로그램을 작성하라.

입력
입력 데이터는 표준입력을 사용한다. 입력은 T개의 테스트 데이터로 구성된다. 입력의 첫 번째 줄에는 입력 데이터의 수를 나타내는 정수 T가 주어진다. 각 테스트 데이터의 첫째 줄에는 Q에 적용할 연산의 개수를 나타내는 정수 k (k ≤ 1,000,000)가 주어진다. 이어지는 k 줄 각각엔 연산을 나타내는 문자(‘D’ 또는 ‘I’)와 정수 n이 주어진다. ‘I n’은 정수 n을 Q에 삽입하는 연산을 의미한다. 동일한 정수가 삽입될 수 있음을 참고하기 바란다. ‘D 1’는 Q에서 최댓값을 삭제하는 연산을 의미하며, ‘D -1’는 Q 에서 최솟값을 삭제하는 연산을 의미한다. 최댓값(최솟값)을 삭제하는 연산에서 최댓값(최솟값)이 둘 이상인 경우, 하나만 삭제됨을 유념하기 바란다.

만약 Q가 비어있는데 적용할 연산이 ‘D’라면 이 연산은 무시해도 좋다. Q에 저장될 모든 정수는 -231 이상 231 미만인 정수이다.

출력
출력은 표준출력을 사용한다. 각 테스트 데이터에 대해, 모든 연산을 처리한 후 Q에 남아 있는 값 중 최댓값과 최솟값을 출력하라. 두 값은 한 줄에 출력하되 하나의 공백으로 구분하라. 만약 Q가 비어있다면 ‘EMPTY’를 출력하라.

예제 입력 1 
2
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
I 333
예제 출력 1 
EMPTY
333 -45"""

# 더블 힙 구현 실패
# class doubleHeap:
#     def __init__(self):
#         self.arr = [0]
#         self.size = 0
#         self.depth = 0

#     def insert(self, x):
#         self.arr.append(x)
#         self.size += 1
#         self.depth = math.floor(math.log2(self.size))
#         if self.size == 1:
#             return
#         current = self.size
#         if self.is_min_area(current):
#             oppsite_idx = (current - 1) // 2 + pow(2, self.depth - 2)
#             if self.arr[oppsite_idx] < x:
#                 self.swap(oppsite_idx, current)
#                 self.swap(oppsite_idx, 2)
#                 self.max_heapify(2)
#             else:
#                 self.swap(current, 1)
#                 self.min_heapify(1)
#         else:
#             # max area
#             oppsite_idx = self.size - pow(2, self.depth -1)
#             if self.arr[oppsite_idx] < x:
#                 self.swap(2, self.size)
#             else:
#                 self.swap(oppsite_idx, self.size)
#                 self.swap(2, self.size)
#                 self.min_heapify(1)
#             self.max_heapify(2)
            


#     def delete(self, flag):
#         if self.size == 0:
#             return
#         if flag == 1:
#             # 맥스 삭제
#             if self.size <= 2:
#                 self.arr.pop()
#                 self.size -= 1
#                 return
#             if self.is_min_area(self.size):
#                 # 현재 크기가 최소 영역일 때,
#                 i = pow(2, self.depth - 1) - 1
#                 self.swap(self.size, 2)
#                 self.arr.pop()
#                 self.size -= 1
#                 self.depth = math.floor(math.log2(self.size))
#             else:
#                 # 현재 크기가 최대 영역일 때,
#                 self.swap(2, self.size)
#                 self.arr.pop()
#                 self.size -= 1
            
#             if self.size >= 2:
#                 self.max_heapify(2)
        
#         else:
#             # 최소 삭제
#             if self.size == 1:
#                 self.arr.pop()
#                 self.size -= 1
#                 return 
#             if self.is_min_area(self.size):
#                 # 현재크기가 최소 영역일 때,
#                 self.swap(self.size, 1)
#                 self.arr.pop()
#                 self.size -= 1
#                 self.depth = math.floor(math.log2(self.size))
#             else:
#                 # 현재크기가 최대 영역일 때,
#                 self.swap(self.size, 1)
#                 self.arr.pop()
#                 self.size -= 1
            
#             if self.size >= 1:
#                 self.min_heapify(1)
        

#     def is_min_area(self, i):
#         # 현재 깊이 = log_2 n의 정수 = d
#         if i <= (pow(2,self.depth) - 1 + pow(2, self.depth - 1) - 1):
#             print("왼쪽")
#             return True
#         else:
#             print("오른쪽")
#             return False
        
#     def min_heapify(self, i):
#         if self.is_leaf(i):
#             return
#         left = i * 2 + 1
#         right = i * 2 + 2
#         if self.size >= right and self.arr[left] > self.arr[right]:    
#             if self.arr[i] > self.arr[right]:
#                 self.swap(i, right)
#                 self.min_heapify(right)
#         else:
#             if self.arr[i] > self.arr[left]:
#                 self.swap(i, left)
#                 self.min_heapify(left)
            

#     def max_heapify(self, i):
#         if self.is_leaf(i):
#             return 
#         left = i * 2 + 1
#         right = i * 2 + 2
     
#         if self.size >= right and self.arr[left] < self.arr[right]:
#             if self.arr[i] < self.arr[right]:
#                 self.swap(i, right)
#                 self.max_heapify(right)
#         else:
#             if self.arr[i] < self.arr[left]:
#                 self.swap(i, left)
#                 self.max_heapify(left)
            

#     def is_leaf(self, i):
#         if i * 2 + 1 > self.size:
#             return True
#         elif i * 2 + 2 > self.size:
#             return True
#         return False
#     def swap(self, x, y):
#         temp = self.arr[x]
#         self.arr[x] = self.arr[y]
#         self.arr[y] = temp
#         print(f"swap: x={x}, y={y},{self.arr}")

#     def __str__(self):
#         if self.size == 0:
#             return f"EMPTY"
#         if self.arr[2]:
#             return f"{self.arr[1]} {self.arr[2]}"
#         else:
#             return f"{self.arr[1]} {self.arr[1]}"

#     def __repr__(self):
#         return self.arr.__str__()

import heapq
    

def double_heap():
    T = int(input())

    for _ in range(T):
        max_heap = []
        min_heap = []
        visited = dict()
        k = int(input())
        for i in range(k):
            input_data = input().split()
            c = str(input_data[0])
            n = int(input_data[1])
            if c == 'I':
                # 삽입
                heapq.heappush(max_heap,[-n, i])
                heapq.heappush(min_heap,[n, i])
                visited[i] = True
            else:
                # 삭제
                if n == 1:
                    # 최대값 삭제
                    while(max_heap):
                        num, idx = heapq.heappop(max_heap)
                        if visited[idx]:
                            visited[idx] = False
                            break
                else:
                    while(min_heap):
                        num, idx = heapq.heappop(min_heap)
                        if visited[idx]:
                            visited[idx] = False
                            break
        
        # 출력
        result = ""
        while(max_heap):
            num, idx = heapq.heappop(max_heap)
            if visited[idx]:
                result += f"{-num}"
                break
        while(min_heap):
            num, idx = heapq.heappop(min_heap)
            if visited[idx]:
                result += f" {num}"
                break
        if len(min_heap) == 0:
            print("EMPTY")
        else:
            print(result)

    return result