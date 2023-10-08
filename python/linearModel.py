import tensorflow as tf

class LinearModel():
    def __init__(self,x_train,y_train):
        self.x_train = x_train
        self.y_train = y_train

    def model(self):
        model = tf.keras.Sequential([
            tf.keras.layers.Input(shape=(1,)),
            tf.keras.layers.Dense(units=1)
        ])
        return model
    
    def train(self, model, x_train, y_train):
        # 编译模型
        model.compile(optimizer='sgd', loss='mean_squared_error')

        # 训练模型
        model.fit(x_train, y_train, epochs=1000, verbose=0)

        # 提取模型的参数 a 和 b
        a, b = model.layers[0].get_weights()
        return a,b
    

# # 生成随机输入数据
# np.random.seed(0)
# x_train = np.random.rand(100)  # 生成100个随机数作为输入特征

# # 根据线性关系生成随机输出数据（添加一些噪声）
# true_slope = 2.0
# true_intercept = 1.0
# y_train = true_slope * x_train + true_intercept + np.random.randn(100) * 0.1

# # 定义模型
# model = tf.keras.Sequential([
#     tf.keras.layers.Input(shape=(1,)), # 输入层，1个特征
#     tf.keras.layers.Dense(units=1)    # 输出层，1个输出
# ])

# # 编译模型
# model.compile(optimizer='sgd', loss='mean_squared_error')

# # 训练模型
# model.fit(x_train, y_train, epochs=1000, verbose=1)

# # 提取模型的参数 a 和 b
# a, b = model.layers[0].get_weights()
# print("模型参数 a:", a[0])
# print("模型参数 b:", b[0])