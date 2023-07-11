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

public class AxiomaticDefinition
{
    private String name_;

    private Map<String,TypeDef> tyd_ = new HashMap<>();
    private List<TypeDef> tyd_ordering_ = new ArrayList<>();

    private Map<String,Operator> opd_ = new HashMap<>();
    private List<Operator> opd_ordering_ = new ArrayList<>();

    private Map<String,Axiom> ax_ = new HashMap<>();
    private List<Axiom> ax_ordering_ = new ArrayList<>();

    private String comment_;

    public AxiomaticDefinition(String n, String c)
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

    public boolean hasComment()
    {
        return comment_.length() > 0;
    }

    public void parse(SymbolTable st)
    {
        for (Operator op : opd_ordering_) 
        {
            op.parse(st);
        }
        for (Axiom axm : ax_ordering_)
        {
            axm.parse(st);
        }
    }

// -----------------------------------------------------------------------------
//    TYPE DEF
// -----------------------------------------------------------------------------
    public void addTypeDef(TypeDef tyd)
    {
        tyd_.put(tyd.name(),tyd);
        tyd_ordering_.add(tyd);
    }

    public List<TypeDef> typeDefOrdering()
    {
        return tyd_ordering_;
    }

    public boolean hasTypes()
    {
        return !tyd_.isEmpty();
    }

// -----------------------------------------------------------------------------
//    OPERATOR
// -----------------------------------------------------------------------------
    public void addOperator(Operator opd)
    {
        opd_.put(opd.name(),opd);
        opd_ordering_.add(opd);
    }

    public List<Operator> operatorOrdering()
    {
        return opd_ordering_;
    }

    public boolean hasOperators()
    {
        return !opd_.isEmpty();
    }

// -----------------------------------------------------------------------------
//    AXIOM
// -----------------------------------------------------------------------------
    public void addAxiom(Axiom ax)
    {
        ax_.put(ax.name(), ax);
        ax_ordering_.add(ax);
    }

    public List<Axiom> axiomOrdering()
    {
        return ax_ordering_;
    }

    public boolean hasAxioms()
    {
        return !ax_.isEmpty();
    }

}