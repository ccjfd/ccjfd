package com.linbinghui.csdn.util.executor;



import com.linbinghui.csdn.util.configuration.Configuration;
import com.linbinghui.csdn.util.configuration.MappedStatement;

import java.util.List;

public interface Executor {
    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... parms) throws Exception;
}
