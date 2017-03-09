# FLatBuffers Samples For Android

这个APP演示了FlatBuffers框架的序列化和反序列化。

对比了FlatBuffers和Json解析速度

 * 使用FlatBuffers，解析时间：4ms
 
 * 使用Json，解析时间：653ms
 
<img src=https://raw.githubusercontent.com/zhangkekekeke/FlatBuffers_master/master/app/src/main/assets/first.jpg width=460 height=750 />

 * 如果数据已经被FlatBuffers加载过一次，第二次解析基本不耗时间。

<img src=https://raw.githubusercontent.com/zhangkekekeke/FlatBuffers_master/master/app/src/main/assets/second1.jpg width=460 height=750 />

经过测试，FlatBuffers比Json解析速度快上太多了，是很好的数据传输优化方式。

# Where
# https://github.com/google/flatbuffers
