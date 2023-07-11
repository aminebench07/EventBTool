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

import com.viklauverk.eventbtools.core.Formula;

import java.util.List;
import java.util.LinkedList;

public class RenderTheoryUnicode extends RenderTheory
{

    // TODO: use canvas method to print so that it can be used by Classes that implement this one
    // In Canvas we can define methods that have different behaviour depending on the kind of doc we write

    @Override
    public void visit_TheoryStart(Theory th)
    {
        // skip a line to differentiate theories
        cnvs().startLine();
        cnvs().append(" ");
        cnvs().endLine();
        cnvs().startLine();
        cnvs().keywordLeft("theory");
        cnvs().space();
        cnvs().id(th.localName());
        cnvs().endLine();

        cnvs().hrule();

        if (th.hasComment())
        {
            cnvs().acomment(th.comment());
            cnvs().nl();
        }
    }

    @Override
    public void visit_ImportsStart(Theory th)
    {
        cnvs().startLine();
        cnvs().keywordLeft("imports");
        cnvs().space();
    }

    @Override
    public void visit_Import(Theory th, Theory imp)
    {
        cnvs().id(imp.localName());
        cnvs().space();
    }

    @Override
    public void visit_ImportsEnd(Theory th)
    {
        cnvs().endLine();
    }

    @Override
    public void visit_HeadingComplete(Theory th)
    {
    }

    @Override
    public void visit_TypeParametersStart(Theory th)
    {
        cnvs().startLine();
        cnvs().keyword("type parameters");
        cnvs().endLine();

        cnvs().startAlignments(Canvas.align_2col);
    }

    @Override
    public void visit_TypeParameter(Theory th, TypeParameters type_parameter)
    {
        cnvs().startAlignedLine();
        cnvs().set(type_parameter.name()); //TODO Modify cnvs().set => cnvs().typeParam?
        cnvs().align();
        cnvs().comment(type_parameter.comment());
        cnvs().stopAlignedLine();
    }

    @Override
    public void visit_TypeParametersEnd(Theory th)
    {
        cnvs().stopAlignments();
    }

    @Override
    public void visit_DatatypesStart(Theory th)
    {
        cnvs().startLine();
        cnvs().keyword("datatypes");
        cnvs().endLine();

        cnvs().startAlignments(Canvas.align_3col);  
    }

    @Override
    public void visit_Datatype(Theory th, Datatype datatype)
    {
        cnvs().startAlignedLine();
        cnvs().append(cnvs().colorize(Canvas.Red, datatype.name()));

        // TODO: This is code duplication from visit_Operator
        if (datatype.hasTypeArguments())
        {
            cnvs().append("(");
            int i = 0;
            for (CarrierSet ta : datatype.typeArgumentsOrdering()) {
                if (i > 0) cnvs().append(", ");
                if (i == 0) i++;
                cnvs().set(ta.name());
            }
            cnvs().append(")");
        }

        stopAlignedLineAndHandlePotentialComment(datatype.comment(), cnvs(), null);

        for (Operator cst : datatype.constructorsOrdering())
        {
            visit_Operator(th, cst);
        }
    }

    @Override
    public void visit_DatatypesEnd(Theory th)
    {
        cnvs().stopAlignments();
    }

    @Override
    public void visit_WDConditionsStart(Theory th)
    {
        //TODO
    }

    @Override
    public void visit_WDCondition(Theory th, WDConditions wdc)
    {
        cnvs().startAlignedLine();
        cnvs().renderAttributes().setAtLabel(false);
        cnvs().set("    WD");
        cnvs().renderAttributes().setAtLabel(true);
        cnvs().align();
        cnvs().startMath();
        wdc.writeFormulaStringToCanvas(cnvs());
        cnvs().stopMath();
        stopAlignedLineAndHandlePotentialComment(wdc.comment(), cnvs(), null);
    }

    @Override
    public void visit_WDConditionsEnd(Theory th)
    {
        //TODO
    }

    @Override
    public void visit_OperatorsStart(Theory th)
    {
        cnvs().startLine();
        cnvs().keyword("operators");
        cnvs().endLine();

        cnvs().startAlignments(Canvas.align_3col);        
    }

    @Override
    public void visit_Operator(Theory th, Operator operator)
    {
        cnvs().startAlignedLine();
        cnvs().label(operator.name());

        if (operator.hasArguments())
        {
            cnvs().append("(");
            int i = 0;
            for (Arguments arg : operator.argumentsOrdering()) {
                if (i > 0) cnvs().append(", ");
                if (i == 0) i++;
                cnvs().append(arg.name() + ":");
                cnvs().startMath();
                arg.getType().writeFormulaStringToCanvas(cnvs());
                cnvs().stopMath();
            }
            cnvs().append(")");
        }

        if (operator.hasReturnType()) 
        {
            cnvs().append(" : ");
            operator.getReturnType().writeFormulaStringToCanvas(cnvs());
        }

        cnvs().align();
        if (operator.hasDirectDefinition()) {
            cnvs().append("= ");
            cnvs().startMath();
            operator.getDef().writeFormulaStringToCanvas(cnvs());
            cnvs().stopMath();
        }
        stopAlignedLineAndHandlePotentialComment(operator.comment(), cnvs(), null);
        
        for (String argName : operator.getRecursiveArgs())
        {
            cnvs().startAlignedLine();
            cnvs().variable("CASE "+argName);
            stopAlignedLineAndHandlePotentialComment(operator.getRecursiveCaseComment(argName), cnvs(), null);
            for (IsAFormula[] rec_def : operator.getRecursiveDefs(argName))
            {
                cnvs().startAlignedLine();
                cnvs().startMath();
                rec_def[0].writeFormulaStringToCanvas(cnvs());
                cnvs().append(cnvs().colorize(Canvas.Red, " --> "));
                rec_def[1].writeFormulaStringToCanvas(cnvs());
                cnvs().stopMath();
                stopAlignedLineAndHandlePotentialComment(rec_def[1].comment(), cnvs(), null);
            }
        }
        
        // WD conditions
        if (operator.hasWdcs())
        {
            visit_WDConditionsStart(th);
            for(WDConditions wdc : operator.wdcsOrdering())
            {
                visit_WDCondition(th, wdc);
            }
            visit_WDConditionsEnd(th);
        }
    }

    @Override
    public void visit_OperatorsEnd(Theory th)
    {
        cnvs().stopAlignments();
    }

    @Override
    public void visit_AxiomaticDefinitionsStart(Theory th)
    {
        cnvs().startLine();
        cnvs().keyword("axiomatic definitions");
        cnvs().endLine();

        cnvs().startAlignments(Canvas.align_3col);        
    }

    @Override
    public void visit_AxiomaticDefinition(Theory th, AxiomaticDefinition axiomatic_definition)
    {
        if (axiomatic_definition.hasOperators()) {
            cnvs().startAlignedLine();
            cnvs().variable("TYPES");
            stopAlignedLineAndHandlePotentialComment("", cnvs(), null);
        }

        for (TypeDef td : axiomatic_definition.typeDefOrdering()) {
            cnvs().startAlignedLine();
            cnvs().set(td.name());
            stopAlignedLineAndHandlePotentialComment(td.comment(), cnvs(), null);
        }

        if (axiomatic_definition.hasOperators()) {
            cnvs().startAlignedLine();
            cnvs().variable("OPERATORS");
            stopAlignedLineAndHandlePotentialComment("", cnvs(), null);
        }

        for (Operator op : axiomatic_definition.operatorOrdering())
        {
            visit_Operator(th, op);
        }

        if (axiomatic_definition.hasAxioms()) {
            cnvs().startAlignedLine();
            cnvs().variable("AXIOMS");
            stopAlignedLineAndHandlePotentialComment("", cnvs(), null);
        }

        for (Axiom axm : axiomatic_definition.axiomOrdering()) {
            cnvs().startAlignedLine();
            cnvs().label(axm.name());
            cnvs().align();
            cnvs().startMath();
            axm.writeFormulaStringToCanvas(cnvs());
            cnvs().stopMath();
            stopAlignedLineAndHandlePotentialComment(axm.comment(), cnvs(), null);
        }
    }

    @Override
    public void visit_AxiomaticDefinitionsEnd(Theory th)
    {
        cnvs().stopAlignments();
    }

    @Override
    public void visit_TheoremsStart(Theory th)
    {
        cnvs().startLine();
        cnvs().keyword("theorems");
        cnvs().endLine();

        cnvs().startAlignments(Canvas.align_3col);        
    }

    @Override
    public void visit_Theorem(Theory th, Theorem theorem)
    {
        cnvs().startAlignedLine();
        cnvs().label(theorem.name());
        cnvs().align();
        cnvs().startMath();
        theorem.writeFormulaStringToCanvas(cnvs());
        cnvs().stopMath();
        stopAlignedLineAndHandlePotentialComment(theorem.comment(), cnvs(), null);
    }

    @Override
    public void visit_TheoremsEnd(Theory th)
    {
        cnvs().stopAlignments();
    }

    @Override
    public void visit_TheoryEnd(Theory th)
    {
        cnvs().startLine();
        cnvs().keyword("end");
        cnvs().endLine();
    }

}