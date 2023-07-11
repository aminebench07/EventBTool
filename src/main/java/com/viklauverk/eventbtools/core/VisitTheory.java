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

public class VisitTheory
{
    /** Walk the Event-B theory th and invoke the visitor functions in rt
        for each part of the th. Also only visit parts that matches the pattern.
    */
    public static void walk(RenderTheory rt, Theory th, String pattern)
    {
        boolean m = Util.match(th.name()+"/", pattern);
        if (m) rt.visit_TheoryStart(th);

        if (m && th.hasImports())
        {
            boolean i = Util.match(th.name()+"/imports/", pattern);
            if (i) rt.visit_ImportsStart(th);
            for (Theory imp : th.importsOrdering())
            {
                boolean ii = Util.match(th.name()+"/imports/"+imp.name()+"/", pattern);
                if (ii) rt.visit_Import(th, imp);
            }
            if (i) rt.visit_ImportsEnd(th);
        }

        if (m) rt.visit_HeadingComplete(th);

        if (th.hasTypeParameters())
        {
            boolean t = Util.match(th.name()+"/type_parameters/", pattern);

            if (t) rt.visit_TypeParametersStart(th);
            for (TypeParameters tp : th.typeParametersOrdering())
            {
                boolean tt = Util.match(th.name()+"/type_parameters/"+tp.name()+"/", pattern);
                if (tt)
                {
                    rt.visit_TypeParameter(th, tp);
                }
            }
            if (t) rt.visit_TypeParametersEnd(th);
        }

        if (th.hasDatatype())
        {
            boolean d = Util.match(th.name()+"/datatypes/", pattern);

            if (d) rt.visit_DatatypesStart(th);
            for (Datatype dt : th.datatypesOrdering())
            {
                boolean dd =  Util.match(th.name()+"/datatypes/"+dt.name()+"/", pattern);
                if (dd) rt.visit_Datatype(th, dt);
            }
            if (d) rt.visit_DatatypesEnd(th);
        }

        if (th.hasOperator())
        {
            boolean o = Util.match(th.name()+"/operators/", pattern);

            if (o) rt.visit_OperatorsStart(th);
            for (Operator op : th.operatorOrdering())
            {
                boolean oo =  Util.match(th.name()+"/operators/"+op.name()+"/", pattern);
                if (oo) rt.visit_Operator(th, op);
            }
            if (o) rt.visit_OperatorsEnd(th);
        }

        if (th.hasAxiomaticDefinition())
        {
            boolean a = Util.match(th.name()+"/axiomatic_definitions/", pattern);

            if (a) rt.visit_AxiomaticDefinitionsStart(th);
            for (AxiomaticDefinition axd : th.axiomaticDefinitionOrdering())
            {
                boolean aa =  Util.match(th.name()+"/axiomatic_definitions/"+axd.name()+"/", pattern);
                if (aa) rt.visit_AxiomaticDefinition(th, axd);
            }
            if (a) rt.visit_AxiomaticDefinitionsEnd(th);
        }

        if (th.hasTheorems())
        {
            boolean t = Util.match(th.name()+"/theorems/", pattern);

            if (t) rt.visit_TheoremsStart(th);
            for (Theorem the : th.theoremOrdering())
            {
                boolean tt =  Util.match(th.name()+"/theorems/"+the.name()+"/", pattern);
                if (tt) rt.visit_Theorem(th, the);
            }
            if (t) rt.visit_TheoremsEnd(th);
        }

        if (m) rt.visit_TheoryEnd(th);
    
    }
}
