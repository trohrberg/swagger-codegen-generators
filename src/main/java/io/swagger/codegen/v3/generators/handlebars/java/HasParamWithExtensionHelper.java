package io.swagger.codegen.v3.generators.handlebars.java;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import io.swagger.codegen.v3.CodegenContent;
import io.swagger.codegen.v3.CodegenParameter;

import java.io.IOException;
import java.util.function.Predicate;

public class HasParamWithExtensionHelper implements Helper<CodegenContent> {

    private Predicate<CodegenParameter> hasExtensionKey(Object extensionKey) { return param -> param.vendorExtensions.containsKey(extensionKey);}

    @Override
    public Object apply(CodegenContent codegenContent, Options options) throws IOException {
        if (options.params.length == 1) {
            Object extensionKey = options.param(0);
            if (codegenContent.getParameters().stream().anyMatch(hasExtensionKey(extensionKey))) {
                return options.fn();
            }
        }

        return options.inverse();
    }
}
