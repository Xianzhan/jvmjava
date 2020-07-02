package com.github.xianzhan.jvmjava.java.runtime.heap.method;

import com.github.xianzhan.jvmjava.java.util.Symbol;

/**
 * 方法描述符解析器
 *
 * @author xianzhan
 * @since 2020-06-28
 */
public class JMethodDescriptorParser {

    private String            raw;
    private int               offset;
    private JMethodDescriptor parsed;

    public static JMethodDescriptor parseMethodDescriptor(String descriptor) {
        var parser = new JMethodDescriptorParser();
        return parser.parse(descriptor);
    }

    private JMethodDescriptor parse(String descriptor) {
        raw = descriptor;
        parsed = new JMethodDescriptor();

        startParams();
        parseParamTypes();
        endParams();
        parseReturnType();
        finish();

        return parsed;
    }

    private void startParams() {
        if (readChar() != '(') {
            causePanic();
        }
    }

    private void endParams() {
        if (readChar() != ')') {
            causePanic();
        }
    }

    private void finish() {
        if (offset != raw.length()) {
            causePanic();
        }
    }

    private void parseParamTypes() {
        do {
            var t = parseFieldType();
            if (t.isEmpty()) {
                break;
            }
            parsed.addParameterType(t);
        } while (true);
    }

    private String parseFieldType() {
        return switch (readChar()) {
            case 'B' -> Symbol.DESCRIPTOR_BYTE;
            case 'C' -> Symbol.DESCRIPTOR_CHAR;
            case 'D' -> Symbol.DESCRIPTOR_DOUBLE;
            case 'F' -> Symbol.DESCRIPTOR_FLOAT;
            case 'I' -> Symbol.DESCRIPTOR_INT;
            case 'J' -> Symbol.DESCRIPTOR_LONG;
            case 'S' -> Symbol.DESCRIPTOR_SHORT;
            case 'Z' -> Symbol.DESCRIPTOR_BOOLEAN;
            case 'L' -> parseObjectType();
            case '[' -> parseArrayType();
            default -> {
                unreadChar();
                yield "";
            }
        };
    }

    private String parseObjectType() {
        var unread = raw.substring(offset);
        var semicolonIndex = unread.indexOf(';');
        if (semicolonIndex == -1) {
            causePanic();
            return "";
        }

        var objStart = offset - 1;
        var objEnd = offset + semicolonIndex + 1;
        offset = objEnd;

        return raw.substring(objStart, objEnd);
    }

    private String parseArrayType() {
        var arrStart = offset - 1;
        parseFieldType();
        var arrEnd = offset;

        return raw.substring(arrStart, arrEnd);
    }

    private void parseReturnType() {
        if (readChar() == 'V') {
            parsed.returnType = Symbol.DESCRIPTOR_VOID;
            return;
        }

        unreadChar();
        var t = parseFieldType();
        if (t.isEmpty()) {
            causePanic();
        }

        parsed.returnType = t;
    }

    private char readChar() {
        return raw.charAt(offset++);
    }

    private void unreadChar() {
        offset--;
    }

    private void causePanic() {
        throw new RuntimeException("BAD descriptor: " + raw);
    }
}
