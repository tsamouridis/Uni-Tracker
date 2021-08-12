from matplotlib import pyplot as plt
import matplotlib
import numpy as np
import sys
import os
matplotlib.rcParams['mathtext.fontset'] = 'cm'
matplotlib.rcParams['font.family'] = 'STIXGeneral'
plt.rcParams['axes.axisbelow'] = True
plt.rcParams["figure.figsize"] = (15,10)
plt.rcParams['axes.titlesize'] = 20
plt.rcParams['axes.labelsize'] = 20

from matplotlib.ticker import MaxNLocator


# prepare data
data = []
n = len(sys.argv) 
for i in range(1, n):
    data.append(float(sys.argv[i]))
np.linspace(0, 10, num = n-1)

# pie
fig0 = plt.figure()
labels = ["Passed", "Not Passed"]
colors = ['green', 'red']
numOfPassed = 0
for i in data:
    if i >= 5:
        numOfPassed += 1
NotPassed = len(data) - numOfPassed
explode = (0, 0.1)  
pieData = [numOfPassed, NotPassed]
plt.title("Passed Courses: "+ str(numOfPassed)+'/'+str(len(data)),fontweight="bold")
plt.pie(pieData, explode = explode, labels=labels, shadow=True, autopct='%1.1f%%', colors=colors,textprops={'fontsize': 20})
fig0.savefig('media/1_pie.png')

# Boxplot
fig1 = plt.figure()
plt.boxplot(data)
plt.title("Boxplot",fontweight="bold")
plt.ylabel("Grades")
plt.ylim(-1, 11)
x = np.linspace(0.8, 1.2, 2)
plt.plot(x,[5, 5],'r--')
ax = plt.gca()
ax.grid(True)
plt.yticks([0,1,2,3,4,5,6,7,8,9,10])
plt.xticks([])
fig1.savefig('media/2_boxplot.png')

# Histogram
b = [0,0.5,1,1.5,2,2.5,3,3.5,4,4.5,5,5.5,6,6.5,7,7.5,8,8.5,9,9.5,10,10.5]
fig2 = plt.figure()
plt.hist(data, edgecolor = 'black', linewidth = 1.2, bins = b)
plt.title("Histogram",fontweight="bold")
plt.xlabel("Grades")
plt.ylabel("Number of Courses")
plt.xlim(0, 11)
plt.grid(True)
ax = plt.gca()
plt.xticks([0,1,2,3,4,5,6,7,8,9,10])
ax.yaxis.set_major_locator(MaxNLocator(integer=True))
fig2.savefig('media/3_histogram.png')

# open images
os.system('start media/0_welcomeIm.png')