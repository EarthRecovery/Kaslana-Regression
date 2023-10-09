import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'
import tensorflow as tf
import numpy as np
import pandas as pd

def random_dataset_gengerate(a,b):
    # 生成随机输入数据
    np.random.seed(0)
    x_train = np.random.rand(100)  # 生成100个随机数作为输入特征

    # 根据线性关系生成随机输出数据（添加一些噪声）
    true_slope = 2.0
    true_intercept = 1.0
    y_train = a * x_train + b + np.random.randn(100) * 0.1
    return x_train,y_train

def getDataFromXls(path):
    # 读取 Excel 文件
    df = pd.read_excel(path, sheet_name='Points')

    x_train = df['X'].values
    y_train = df['Y'].values
    return x_train,y_train


def main():
    LinearModel = __import__('linearModel').LinearModel
    x_train,y_train = getDataFromXls('..\KaslanaRegressionBackEnd\points.xls')
    modelClass = LinearModel(x_train,y_train)
    linearModel = modelClass.model()
    a,b = modelClass.train(linearModel,x_train,y_train)
    print(a[0][0])
    print(b[0])

def test():
    getDataFromXls('..\KaslanaRegressionBackEnd\points.xls')

if __name__ == '__main__':
    main()