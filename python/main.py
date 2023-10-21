import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'
import tensorflow as tf
import numpy as np
import pandas as pd
from sklearn.linear_model import LinearRegression

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
    # LinearModel = __import__('linearModel').LinearModel
    # x_train,y_train = getDataFromXls('..\KaslanaRegressionBackEnd\points.xls')
    # modelClass = LinearModel(x_train,y_train)
    # linearModel = modelClass.model()
    # a,b = modelClass.train(linearModel,x_train,y_train)
    # print(a[0][0])
    # print(b[0])



    x_train,y_train = getDataFromXls('..\KaslanaRegressionBackEnd\points.xls')
    # 创建线性回归模型
    model = LinearRegression()

    # 拟合模型
    x_train = np.array(x_train).reshape(-1, 1)
    y_train = np.array(y_train).reshape(-1, 1)
    model.fit(x_train, y_train)

    # 获取模型参数
    slope = model.coef_[0]
    intercept = model.intercept_

    print(slope[0])
    print(intercept[0])
    # mse
    y_pred = model.predict(x_train)
    mse = np.mean((y_train - y_pred)**2)
    print(mse)

def test():
    getDataFromXls('..\KaslanaRegressionBackEnd\points.xls')

if __name__ == '__main__':
    main()