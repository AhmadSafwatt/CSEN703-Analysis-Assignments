# -*- coding: utf-8 -*-
"""
@Code written by: Ahmad Safwat

"""
import time
import numpy as np
import matplotlib.pyplot as plt

def iterativeMethod(a, n):
    result = 1
    while n > 0:
        result = result * a
        n = n - 1
    return result

def divideAndConquer(a, n):
    if n == 0:
        return 1
    else:
        val = divideAndConquer(a, n//2)
        time.sleep(0.1)
        if n % 2 == 0:
            return val**2
        else:
            return (val**2) * a

i = 1
j = 1
iterativeTime = []
divideAndConquerTime = []
t = [10**i for i in range(6)]

while i < 10**6:
    startTime = time.time()
    iterativeMethod(2, i)
    endTime = time.time()
    timeTaken = endTime - startTime
    iterativeTime.append(timeTaken)
    i = i * 10


while j < 10**6:
    startTime = time.time()
    divideAndConquer(2, j)
    endTime = time.time()
    timeTaken = endTime - startTime
    divideAndConquerTime.append(timeTaken)
    j = j * 10

plt.figure()
plt.plot(t,iterativeTime, label='Iterative')
plt.plot(t, divideAndConquerTime, label= 'Divide and Conquer')
plt.xlabel('n value')
plt.ylabel('Execution time in seconds')
plt.legend()
plt.grid()

