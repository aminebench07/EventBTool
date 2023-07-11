/*
 Copyright (C) 2021 Viklauverk AB
 Author Alexandre Hamoir

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

import java.text.Normalizer.Form;

public class RenderFormulaWhy extends RenderFormulaUnicode {

    //TODO: all complex expressions are parenthesised, to remove them search for "cnvs().symbol("(")" with " //p" at the end and "cnvs().symbol(")")" with the " //q" at the end.

    public RenderFormulaWhy(Canvas canvas)
    {
        super(canvas);
        limitToAscii();
    }

    @Override public Formula visit_FALSE(Formula i)
    {
        cnvs().symbol("false"); visitMeta(i); return i;
    }

    @Override public Formula visit_TRUE(Formula i)
    {
        cnvs().symbol("true"); visitMeta(i); return i;
    }

    @Override public Formula visit_APPLICATION(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("image "); visitLeft(i); cnvs().symbol(" "); visitRight(i); visitMeta(i); 
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_CONJUNCTION(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i);
        checkNewLineBefore(i);
        cnvs().symbol(" /\\ ");
        visitMeta(i);
        checkNewLineAfter(i);
        visitRight(i); 
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_IMPLICATION(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(" -> "); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_NEGATION(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("not "); visitMeta(i); visitChild(i); 
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_DISJUNCTION(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i);
        checkNewLineBefore(i);
        cnvs().symbol(" \\/ ");
        visitMeta(i);
        checkNewLineAfter(i);
        visitRight(i); 
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_EQUIVALENCE(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(" <-> "); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_UNIVERSALQ(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("forall "); visitMeta(i); visitLeft(i); cnvs().symbol("."); cnvs().endLine();
        cnvs().startIndent(); cnvs().startIndentedLine(); visitRight(i);
        cnvs().symbol(")"); //q
        cnvs().endIndent();
        return i;
    }

    @Override public Formula visit_EXISTENTIALQ(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("exists "); visitMeta(i); visitLeft(i); cnvs().symbol("."); cnvs().endLine();
        cnvs().startIndent(); cnvs().startIndentedLine(); visitRight(i);
        cnvs().symbol(")"); //q
        cnvs().endLine();
        cnvs().endIndent();
        cnvs().startIndentedLine();
        return i;
    }

    @Override public Formula visit_EQUALS(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(" = "); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_NOT_EQUALS(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(" <> "); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_LESS_THAN(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(" < "); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_GREATER_THAN(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(" > "); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_LESS_THAN_OR_EQUAL(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(" <= "); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_GREATER_THAN_OR_EQUAL(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(" >= "); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_CHOICE(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("choose "); visitChild(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_MEMBERSHIP(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("mem "); visitMeta(i); visitLeft(i); cnvs().symbol(" "); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_NOT_MEMBERSHIP(Formula i)
    {
        innerVisit(FormulaFactory.newNegation(
            FormulaFactory.newSetMembership(i.left(), i.right(), null), 
            i.meta()));
        return i;    
    }

    @Override public Formula visit_SUBSET(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("subset "); visitMeta(i); visitLeft(i); cnvs().symbol(" "); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_STRICT_SUBSET(Formula i)
    {
        innerVisit(FormulaFactory.newConjunction(
            FormulaFactory.newSubSet(i.left(), i.right(), null),
            FormulaFactory.newNotEquals(i.left(), i.right(), null),
            i.meta()
            ));
        return i;
    }

    @Override public Formula visit_NOT_SUBSET(Formula i)
    {
        innerVisit(FormulaFactory.newNegation(
            FormulaFactory.newSubSet(i.left(), i.right(), null),
            i.meta()
            ));
        return i;
    }

    @Override public Formula visit_NOT_STRICT_SUBSET(Formula i)
    {
        innerVisit(FormulaFactory.newNegation(
            FormulaFactory.newStrictSubSet(i.left(), i.right(), null),
            i.meta()
            ));
        return i;
    }

    //TODO: visit_FINITE

    //TODO: visit_PARTITION

    //TODO: visit_OF_TYPE

    @Override public Formula visit_SET_UNION(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("union "); visitMeta(i); visitLeft(i); cnvs().symbol(" "); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_SET_INTERSECTION(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("inter "); visitMeta(i); visitLeft(i); cnvs().symbol(" "); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_CARTESIAN_PRODUCT(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("times "); visitMeta(i); visitLeft(i); cnvs().symbol(" "); visitRight(i); 
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_RELATION(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("relation "); visitMeta(i); visitLeft(i); cnvs().symbol(" "); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    //TODO: some of the relation symbols below are not defined in why3 yet
    @Override public Formula visit_TOTAL_RELATION(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(c(" ", "<<->")); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_SURJECTIVE_RELATION(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(c(" ", "<->>")); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_SURJECTIVE_TOTAL_RELATION(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(c(" ", "<<->>")); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_PARTIAL_FUNCTION(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(c("⇸ ", "+->")); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_TOTAL_FUNCTION(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(c("→ ", "-->")); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_PARTIAL_INJECTION(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(c("⤔ ", ">+>")); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_TOTAL_INJECTION(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(c("↣ ", ">->")); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_PARTIAL_SURJECTION(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(c("⤀ ", "+->>")); visitMeta(i); visitRight(i);        
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_TOTAL_SURJECTION(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(c("↠ ", "-->>")); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_TOTAL_BIJECTION(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(c("⤖ ", ">->>")); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }
    
    @Override public Formula visit_FORWARD_COMPOSITION(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("semicolon "); visitMeta(i); visitLeft(i); cnvs().symbol(" "); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    //TODO: visit_BACKWARD_COMPOSITION

    @Override public Formula visit_DOMAIN_RESTRICTION(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(" <| "); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_DOMAIN_SUBTRACTION(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(" <<| "); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_RANGE_RESTRICTION(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(" |> "); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_RANGE_SUBTRACTION(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(" |>> "); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_OVERRIDE(Formula i)
    {
        cnvs().symbol("("); //p
        visitLeft(i); cnvs().symbol(" <+ "); visitMeta(i); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_DIRECT_PRODUCT(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("direct_product "); visitMeta(i); visitLeft(i); cnvs().symbol(" "); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_PARALLEL_PRODUCT(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("parallel_product "); visitMeta(i); visitLeft(i); cnvs().symbol(" "); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_POWER_SET(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("power "); visitMeta(i); visitChild(i);
        cnvs().symbol(")"); //q
        return i;
    }

    //TODO: visit_POWER1_SET

    //TODO: visit_Q_UNION

    //TODO: visit_Q_INTER

    //TODO: visit_LAMBDA_ABSTRACTION

    //TODO: visit_SET_COMPREHENSION

    //TODO: visit_ENUMERATED_SET

    //TODO: visit_LIST_OF_VARIABLES

    //TODO: visit_LIST_OF_NONFREE_VARIABLES

    //TODO: visit_LIST_OF_EXPRESSIONS

    @Override public Formula visit_MULTIPLICATION(Formula i)
    {
        visitLeft(i); cnvs().symbol(" * "); visitMeta(i); visitRight(i); return i;
    }

    @Override public Formula visit_DIVISION(Formula i)
    {
        visitLeft(i); cnvs().symbol(" / "); visitMeta(i); visitRight(i); return i;
    }

    //TODO: visit_UP_TO

    @Override public Formula visit_EMPTY_SET(Formula i)
    {
        cnvs().symbol("empty"); return i; //TODO: in why3 empty may need to be typed "empty : set 'a" for instance
    }

    @Override public Formula visit_NAT_SET(Formula i)
    {
        cnvs().symbol("natural"); return i;
    }

    @Override public Formula visit_NAT1_SET(Formula i)
    {
        cnvs().symbol("natural1"); return i;
    }

    @Override public Formula visit_INT_SET(Formula i)
    {
        cnvs().symbol("integer"); return i;
    }
    
    @Override public Formula visit_MAPSTO(Formula i)
    {
        cnvs().symbol("("); visitLeft(i); cnvs().symbol(", "); visitMeta(i); visitRight(i); cnvs().symbol(")"); return i;
    }

    @Override public Formula visit_TYPE_PARAMETER_SYMBOL(Formula i)
    {
        visit_TYPED(i);
        return i;
    }

    @Override public Formula visit_TYPEDEF_SYMBOL(Formula i)
    {
        visit_TYPED(i);
        return i;
    }

    @Override public Formula visit_INVERT(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("inverse "); visitMeta(i); visitChild(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_RELATION_IMAGE(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("image "); visitMeta(i); visitLeft(i); cnvs().symbol(" "); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    //TODO: visit_G_UNION

    //TODO: visit_G_INTER

    @Override public Formula visit_DOMAIN(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("dom "); visitMeta(i); visitChild(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_RANGE(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("ran "); visitMeta(i); visitChild(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_SET_MINUS(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("diff "); visitMeta(i); visitLeft(i); cnvs().symbol(" "); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    //TODO: visit_SET_COMPREHENSION_SPECIAL

    @Override public Formula visit_ADDITION(Formula i)
    {
        visitLeft(i); cnvs().symbol(" + "); visitMeta(i); visitRight(i); return i;
    }

    @Override public Formula visit_SUBTRACTION(Formula i)
    {
        visitLeft(i); cnvs().symbol(" - "); visitMeta(i); visitRight(i); return i;
    }

    //TODO: visit_MODULO

    //TODO: visit_EXPONENTIATION

    //TODO: visit_MINIMUM

    //TODO: visit_MAXIMUM

    //TODO: visit_TEST_BOOL

    @Override public Formula visit_CARDINALITY(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("card"); visitMeta(i); visitChild(i);
        cnvs().symbol(")"); //q
        return i;
    }

    //TODO: visit_ID_SET

    //TODO: visit_PRJ1

    //TODO: visit_PRJ2

    //TODO: visit_BOOL_SET

    @Override public Formula visit_FUNC_APP(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("apply "); visitMeta(i); visitLeft(i); cnvs().symbol(" "); visitRight(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_FUNC_INV_APP(Formula i)
    {
        innerVisit(FormulaFactory.newFunctionApplication(
            FormulaFactory.newInvert(i.left(), null),
            i.right(), 
            i.meta()));
        return i;
    }

    @Override public Formula visit_OPERATOR_EXPRESSION(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("op_");
        visitChildNum(i, 0); 
        for (int j = 1; j < i.numChildren(); j++) {
            cnvs().symbol(" ");
            visitChildNum(i, j);
        }
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_INFIX_OPERATOR_EXPRESSION(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("op_");
        visitChildNum(i, 0);
        for (int j = 1; j <= 2; j++) {
            cnvs().symbol(" ");
            visitChildNum(i, j);
        }
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_DATATYPE(Formula i)
    {
        visit_TYPED(i);
        return i;
    }

    @Override public Formula visit_CONSTRUCTOR(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("Cst_");
        visitChildNum(i, 0);
        for (int j = 1; j < i.numChildren(); j++) {
            cnvs().symbol(" ");
            visitChildNum(i, j);
        }
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_DESTRUCTOR(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("dst_");
        visitChildNum(i, 0);
        for (int j = 1; j < i.numChildren(); j++) {
            cnvs().symbol(" ");
            visitChildNum(i, j);
        }
        cnvs().symbol(")"); //q
        return i;
    }

    // visit_TYPED

    @Override public Formula visit_TYPED(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("all : set "); innerVisitTyped(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_TYPED_INT_SET(Formula i)
    {
        cnvs().symbol("int"); visitMeta(i); return i;
    }

    @Override public Formula visit_TYPED_POWER_SET(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("set "); visitMeta(i); visitChildTyped(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_TYPED_CARTESIAN_PRODUCT(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("set ("); visitMeta(i); visitLeftTyped(i); cnvs().symbol(","); visitRightTyped(i); cnvs().symbol(")");
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_TYPED_RELATION(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("rel "); visitLeftTyped(i); cnvs().symbol(" "); visitRightTyped(i);
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_TYPED_DATATYPE(Formula i)
    {
        cnvs().symbol("("); //p
        cnvs().symbol("dt_");
        visitChildNum(i, 0);
        for (int j = 1; j < i.numChildren(); j++) {
            cnvs().symbol(" ");
            visitChildNumTyped(i, j);
        }
        cnvs().symbol(")"); //q
        return i;
    }

    @Override public Formula visit_TYPED_TYPE_PARAMETER_SYMBOL(Formula i)
    {
        cnvs().set("'tp_"+Symbols.name(i.intData())); return i;
    }

    @Override public Formula visit_TYPED_TYPEDEF_SYMBOL(Formula i)
    {
        cnvs().set("td_"+Symbols.name(i.intData())); return i;
    }

    @Override public Formula visit_TYPED_PARENTHESISED_EXPRESSION(Formula i)
    {
        cnvs().symbol("(");
        visitChildTyped(i);
        cnvs().symbol(")");
        visitMeta(i);
        return i;
    }

}
