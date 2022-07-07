package com.wpf.app.quick.compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

final class FieldViewBinding implements MemberViewBinding {
  private final String name;
  private final TypeName type;
  private final boolean required;
  private final boolean groupView;

  FieldViewBinding(String name, TypeName type, boolean required, boolean groupView) {
    this.name = name;
    this.type = type;
    this.required = required;
    this.groupView = groupView;
  }

  public String getName() {
    return name;
  }

  public TypeName getType() {
    return type;
  }

  public ClassName getRawType() {
    if (type instanceof ParameterizedTypeName) {
      return ((ParameterizedTypeName) type).rawType;
    }
    return (ClassName) type;
  }

  @Override public String getDescription() {
    return "field '" + name + "'";
  }

  public boolean isRequired() {
    return required;
  }

  public boolean isGroupView() {
    return groupView;
  }
}
