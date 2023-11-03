# -*- coding: utf-8 -*-
"""
Created on Fri Nov  3 16:16:55 2023

@author: ahmad
"""
import time
import numpy as np
import matplotlib.pyplot as plt
import random

def merge_sort(unSortedArray):
    if len(unSortedArray) <= 1:
        return unSortedArray
    
    mid = len(unSortedArray) // 2
    left = unSortedArray[:mid]
    right = unSortedArray[mid:]
    
    left = merge_sort(left)
    right = merge_sort(right)
    
    return merge(left, right)


def merge(left, right): 
    merged = []
    i = j = 0
    
    while i < len(left) and j < len(right):
        if left[i] < right[j]:
            merged.append(left[i])
            i += 1
        else:
            merged.append(right[j])
            j += 1
    
    merged.extend(left[i:])
    merged.extend(right[j:])
    
    return merged


def binary_search(arr, target):
    low = 0
    high = len(arr) - 1
    
    while low <= high:
        mid = (low + high) // 2
        
        if arr[mid] == target:
            return True
        elif arr[mid] < target:
            low = mid + 1
        else:
            high = mid - 1
    
    return False


def find_pairs_with_sum(arr, target_sum):
    arr = merge_sort(arr)
    pairs = []
    
    for i in range(len(arr)):
        complement = target_sum - arr[i]
        
        if binary_search(arr[i+1:], complement):
            pairs.append((arr[i], complement))
    
    return pairs

i = 1
testSumValue = 8
divideAndConquerTime = []
array = []
t = [10**i for i in range(5)]

while i <= 10**5:
    array.append(random.randint(1, 10)) # limiting the random values in the array from 1 to 10.
    i = i + 1
    if i == 10 or i == 100 or i == 1000 or i == 10000 or i == 100000: #  or i == 1000000:
        startTime = time.time()
        find_pairs_with_sum(array, testSumValue)
        endTime = time.time()
        timeTaken = endTime - startTime
        divideAndConquerTime.append(timeTaken)
        

print(divideAndConquerTime)

plt.figure()
plt.plot(t, divideAndConquerTime, label= 'Divide and Conquer')
plt.xlabel('n value')
plt.ylabel('Execution time in seconds')
plt.legend()
plt.grid()