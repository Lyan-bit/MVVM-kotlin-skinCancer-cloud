package com.example.skincancer.viewmodel;

class OclAttribute {

  OclAttribute() { }

  OclAttribute(String nme)
  { name = nme; }

  static OclAttribute createOclAttribute()
  { OclAttribute result = new OclAttribute();
    return result;
  }

  public void setType(OclType t)
  { type = t; }

  String name = "";
  OclType type = null;

  public String getName()
  { return name; }

  public OclType getType()
  { return type; }

}

