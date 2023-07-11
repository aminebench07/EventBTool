/*
 Copyright (C) 2021 Viklauverk AB
 Author Fredrik Öhrström

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

import java.util.List;
import java.util.LinkedList;

import com.viklauverk.eventbtools.core.Formula;

public class RenderTheory extends CommonRenderFunctions
{
    public void visit_TheoryStart(Theory th) { }

    public void visit_ImportsStart(Theory th) { }
    public void visit_Import(Theory th, Theory imp) { } 
    public void visit_ImportsEnd(Theory th) { }

    public void visit_HeadingComplete(Theory th) { }

    public void visit_TypeParametersStart(Theory th) { }
    public void visit_TypeParameter(Theory th, TypeParameters type_parameter) { }
    public void visit_TypeParametersEnd(Theory th) { }

    public void visit_DatatypesStart(Theory th) { }
    public void visit_Datatype(Theory th, Datatype datatype) { }
    public void visit_DatatypesEnd(Theory th) { }

    public void visit_OperatorsStart(Theory th) { }
    public void visit_Operator(Theory th, Operator operator) { }
    public void visit_OperatorsEnd(Theory th) { }

    public void visit_WDConditionsStart(Theory th) { }
    public void visit_WDCondition(Theory th, WDConditions wdc) { }
    public void visit_WDConditionsEnd(Theory th) { }

    public void visit_AxiomaticDefinitionsStart(Theory th) { }
    public void visit_AxiomaticDefinition(Theory th, AxiomaticDefinition axiomatic_definition) { }
    public void visit_AxiomaticDefinitionsEnd(Theory th) { }

    public void visit_TheoremsStart(Theory th) { }
    public void visit_Theorem(Theory th, Theorem theorem) { }
    public void visit_TheoremsEnd(Theory th) { }

    public void visit_TheoryEnd(Theory th) { }

    protected String buildTheoryPartName(Theory th)
    {
        return th.name();
    }

    protected String buildTheoryTypeParametersPartName(Theory th)
    {
        return th.name()+"/type_parameters";
    }

    protected String buildTheoryTypeParameterPartName(Theory th, TypeParameters tp)
    {
        return th.name()+"/type_parameters/"+tp.name();
    }

    protected String buildTheoryDatatypesPartName(Theory th)
    {
        return th.name()+"/datatypes";
    }

    protected String buildTheoryDatatypePartName(Theory th, Datatype dt)
    {
        return th.name()+"/datatypes/"+dt.name();
    }

    protected String buildTheoryOperatorsPartName(Theory th)
    {
        return th.name()+"/operators";
    }

    protected String buildTheoryOperatorPartName(Theory th, Operator op)
    {
        return th.name()+"/operators/"+op.name();
    }

    protected String buildTheoryAxiomaticDefinitionsPartName(Theory th)
    {
        return th.name()+"/axiomatic_definitions";
    }

    protected String buildTheoryAxiomaticDefinitionPartName(Theory th, AxiomaticDefinition axd)
    {
        return th.name()+"/axiomatic_definitions/"+axd.name();
    }

    protected String buildTheoryTheoremsPartName(Theory th)
    {
        return th.name()+"/theorems";
    }

    protected String buildTheoryTheoremPartName(Theory th, Theorem thm)
    {
        return th.name()+"/theorems/"+thm.name();
    }

    protected String buildTheoryWDConditionsPartName(Theory th)
    {
        return th.name()+"/wd_conditions";
    }
}
