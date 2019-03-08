package com.hellozjf.test.tensorflow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tensorflow.*;

import java.util.List;

/**
 * @author Jingfeng Zhou
 */
@Configuration
@Slf4j
public class BeanConfig {

    /**
     * tensorflow官网给出的例子
     */
    private void offical() throws Exception {
        try (Graph g = new Graph()) {
            final String value = "Hello from " + TensorFlow.version();

            // Construct the computation graph with a single operation, a constant
            // named "MyConst" with a value "value".
            try (Tensor t = Tensor.create(value.getBytes("UTF-8"))) {
                // The Java API doesn't yet include convenience functions for adding operations.
                g.opBuilder("Const", "MyConst").setAttr("dtype", t.dataType()).setAttr("value", t).build();
            }

            // Execute the "MyConst" operation in a Session.
            try (Session s = new Session(g);
                 // Generally, there may be multiple output tensors,
                 // all of them must be closed to prevent resource leaks.
                 Tensor output = s.runner().fetch("MyConst").run().get(0)) {
                log.debug("{}", new String(output.bytesValue(), "UTF-8"));
            }
        }
    }

    /**
     * 网上给出的例子
     * 参考https://blog.csdn.net/ling913/article/details/80186093
     */
    private void model2() throws Exception {
        SavedModelBundle b = SavedModelBundle.load("tensorflow/model2", "mytag");
        Session tfSession = b.session();
        //要执行的op
        Operation operationPredict = b.graph().operation("predict");
        Output output = new Output(operationPredict, 0);
        //构造测试数据，用的是mnist测试集的第15个， mnist.test.images[15]，label是数字5
        float[][] a = new float[1][784];
        a[0] = new float[]{0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.2f, 0.517647f, 0.839216f, 0.992157f, 0.996078f, 0.992157f, 0.796079f, 0.635294f, 0.160784f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.4f, 0.556863f, 0.796079f, 0.796079f, 0.992157f, 0.988235f, 0.992157f, 0.988235f, 0.592157f, 0.27451f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.996078f, 0.992157f, 0.956863f, 0.796079f, 0.556863f, 0.4f, 0.321569f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.67451f, 0.988235f, 0.796079f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.0823529f, 0.87451f, 0.917647f, 0.117647f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.478431f, 0.992157f, 0.196078f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.482353f, 0.996078f, 0.356863f, 0.2f, 0.2f, 0.2f, 0.0392157f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.0823529f, 0.87451f, 0.992157f, 0.988235f, 0.992157f, 0.988235f, 0.992157f, 0.67451f, 0.321569f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.0823529f, 0.839216f, 0.992157f, 0.796079f, 0.635294f, 0.4f, 0.4f, 0.796079f, 0.87451f, 0.996078f, 0.992157f, 0.2f, 0.0392157f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.239216f, 0.992157f, 0.670588f, 0f, 0f, 0f, 0f, 0f, 0.0784314f, 0.439216f, 0.752941f, 0.992157f, 0.831373f, 0.160784f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.4f, 0.796079f, 0.917647f, 0.2f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.0784314f, 0.835294f, 0.909804f, 0.321569f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.243137f, 0.796079f, 0.917647f, 0.439216f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.0784314f, 0.835294f, 0.988235f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.6f, 0.992157f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.160784f, 0.913726f, 0.831373f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.443137f, 0.360784f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.121569f, 0.678431f, 0.956863f, 0.156863f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.321569f, 0.992157f, 0.592157f, 0f, 0f, 0f, 0f, 0f, 0f, 0.0823529f, 0.4f, 0.4f, 0.717647f, 0.913726f, 0.831373f, 0.317647f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.321569f, 1.0f, 0.992157f, 0.917647f, 0.596078f, 0.6f, 0.756863f, 0.678431f, 0.992157f, 0.996078f, 0.992157f, 0.996078f, 0.835294f, 0.556863f, 0.0784314f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.278431f, 0.592157f, 0.592157f, 0.909804f, 0.992157f, 0.831373f, 0.752941f, 0.592157f, 0.513726f, 0.196078f, 0.196078f, 0.0392157f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.0f};
        Tensor input_x = Tensor.create(a);
        List<Tensor<?>> out = tfSession.runner().feed("input_x", input_x).fetch(output).run();
        for (Tensor s : out) {
            float[][] t = new float[1][10];
            s.copyTo(t);
            for (float i : t[0]) {
                System.out.println(i);
            }
        }
    }

    /**
     * 预测图片
     * @throws Exception
     */
    private void model_convnet() throws Exception {
        SavedModelBundle b = SavedModelBundle.load("tensorflow/model_convnet", "mytag");
        Session tfSession = b.session();
        //要执行的op
        Operation operationPredict = b.graph().operation("predict");
        Output output = new Output(operationPredict, 0);
        //构造测试数据，用的是mnist测试集的第15个， mnist.test.images[15]，label是数字5
        float[][] a = new float[1][784];
        a[0] = new float[]{0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.2f, 0.517647f, 0.839216f, 0.992157f, 0.996078f, 0.992157f, 0.796079f, 0.635294f, 0.160784f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.4f, 0.556863f, 0.796079f, 0.796079f, 0.992157f, 0.988235f, 0.992157f, 0.988235f, 0.592157f, 0.27451f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.996078f, 0.992157f, 0.956863f, 0.796079f, 0.556863f, 0.4f, 0.321569f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.67451f, 0.988235f, 0.796079f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.0823529f, 0.87451f, 0.917647f, 0.117647f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.478431f, 0.992157f, 0.196078f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.482353f, 0.996078f, 0.356863f, 0.2f, 0.2f, 0.2f, 0.0392157f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.0823529f, 0.87451f, 0.992157f, 0.988235f, 0.992157f, 0.988235f, 0.992157f, 0.67451f, 0.321569f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.0823529f, 0.839216f, 0.992157f, 0.796079f, 0.635294f, 0.4f, 0.4f, 0.796079f, 0.87451f, 0.996078f, 0.992157f, 0.2f, 0.0392157f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.239216f, 0.992157f, 0.670588f, 0f, 0f, 0f, 0f, 0f, 0.0784314f, 0.439216f, 0.752941f, 0.992157f, 0.831373f, 0.160784f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.4f, 0.796079f, 0.917647f, 0.2f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.0784314f, 0.835294f, 0.909804f, 0.321569f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.243137f, 0.796079f, 0.917647f, 0.439216f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.0784314f, 0.835294f, 0.988235f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.6f, 0.992157f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.160784f, 0.913726f, 0.831373f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.443137f, 0.360784f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.121569f, 0.678431f, 0.956863f, 0.156863f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.321569f, 0.992157f, 0.592157f, 0f, 0f, 0f, 0f, 0f, 0f, 0.0823529f, 0.4f, 0.4f, 0.717647f, 0.913726f, 0.831373f, 0.317647f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.321569f, 1.0f, 0.992157f, 0.917647f, 0.596078f, 0.6f, 0.756863f, 0.678431f, 0.992157f, 0.996078f, 0.992157f, 0.996078f, 0.835294f, 0.556863f, 0.0784314f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.278431f, 0.592157f, 0.592157f, 0.909804f, 0.992157f, 0.831373f, 0.752941f, 0.592157f, 0.513726f, 0.196078f, 0.196078f, 0.0392157f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.0f};
        Tensor input_x = Tensor.create(a);
        List<Tensor<?>> out = tfSession.runner().feed("input_x", input_x).fetch(output).run();
        for (Tensor s : out) {
            float[][] t = new float[1][10];
            s.copyTo(t);
            for (float i : t[0]) {
                System.out.println(i);
            }
        }
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {

        };
    }
}
