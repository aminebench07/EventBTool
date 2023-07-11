/*
 Copyright (C) 2021 Viklauverk AB
 Author Marius Hinge

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.viklauverk.eventbtools.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Datatype
{
    private String name_;
    private String comment_;

    // NB: The type expression or a type argument is a single symbole
    // thus we can use CarrierSet and have the type expression be its name
    private Map<String,TypeParameters> type_arguments_ = new HashMap<>();
    private List<TypeParameters> type_arguments_ordering_ = new ArrayList<>();

    // TODO: For now constructors and destructors are parsed as operators inside formulas 
    // and don't have a class of their own, destructors are the constructor's arguments
    // One may define new symbols in the symbole table to parse them differently,
    // then defining a new way to represent them inside formulas
    private Map<String,Operator> cons_ = new HashMap<>();
    private List<Operator> cons_ordering_ = new ArrayList<>();

    public Datatype(String n, String c)
    {
      name_ = n;
      comment_ = c;
    }

    public String name()
    {
      return name_;
    }

    public String comment()
    {
      return comment_;
    }

    public void addConstructor(Operator cons)
    {
      cons_.put(cons.name(), cons);
      cons_ordering_.add(cons);
    }

    public Operator getConstructor(String name)
    {
      return cons_.get(name);
    }

    public List<Operator> constructorsOrdering()
    {
      return cons_ordering_;
    }

    public void addTypeArgument(TypeParameters type)
    {
      type_arguments_.put(type.name(), type);
      type_arguments_ordering_.add(type);
    }

    public TypeParameters getTypeArgument(String name)
    {
      return type_arguments_.get(name);
    }

    public List<TypeParameters> typeArgumentsOrdering()
    {
      return type_arguments_ordering_;
    }

    public boolean hasTypeArguments()
    {
      return !type_arguments_.isEmpty();
    }

    public void parse(SymbolTable st)
    {
      for (Operator cons : cons_ordering_)
      {
        cons.parse(st);
      }
    }
}
