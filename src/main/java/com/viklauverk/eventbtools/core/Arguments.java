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

public class Arguments extends Typed
{
    private String name_;
    private String comment_;
    private IsAFormula type_;

    public Arguments(String n, String c)
    {
        name_ = n;
        comment_ = c;
    }

    public Arguments(String n, String e, String c)
    {
        name_ = n;
        type_ = new IsAFormula(n+"_def", e, "");
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

    public boolean hasComment()
    {
        return comment_.length() > 0;
    }

    public IsAFormula getType()
    {
      return type_;
    }

    public void parse(SymbolTable st)
    {
      type_.parse(st);
    }
}
