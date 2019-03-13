package com.hellozjf.test.tensorflow;

import lombok.extern.slf4j.Slf4j;
import org.python.util.PythonInterpreter;

import java.util.Properties;

/**
 * @author Jingfeng Zhou
 */
@Slf4j
public class PyInterpreter {

    private static PythonInterpreter pyInterpreter = null;

    /**
     * 初始化Jython解释器环境，参考https://blog.csdn.net/u010882234/article/details/74352492
     * @return
     */
    public static PythonInterpreter getPythonInterpreter() {
        if (pyInterpreter == null) {
            Properties props = new Properties();
            props.put("python.home", "tensorflow/jython-2.7.0");
            props.put("python.console.encoding", "UTF-8");
            props.put("python.security.respectJavaAccessibility", "false");
            props.put("python.import.site", "false");
            Properties preprops = System.getProperties();
            PythonInterpreter.initialize(preprops, props, new String[0]);
            pyInterpreter = new PythonInterpreter();
            pyInterpreter.exec("import sys");
            pyInterpreter.exec("print 'prefix', sys.prefix");
            pyInterpreter.exec("print sys.path");
            log.debug("python的jar包引用正确");
            pyInterpreter = new PythonInterpreter();
        }
        return pyInterpreter;
    }

}
