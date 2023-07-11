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

import com.viklauverk.eventbtools.core.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Attribute;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Theory
{
    private static LogModule log = LogModule.lookup("theory");
    private static LogModule log_codegen = LogModule.lookup("codegen");

    private SymbolTable symbol_table_;

    private String name_;
    private String directory_;

    private Map<String,Theory> imports_ = new HashMap<>();
    private List<Theory> imports_ordering_ = new ArrayList<>();
    private List<String> imports_names_ = new ArrayList<>();

    private Map<String,TypeParameters> type_parameters_ = new HashMap<>();
    private List<TypeParameters> type_parameters_ordering_ = new ArrayList<>();
    private List<String> type_parameters_names_ = new ArrayList<>();

    private Map<String,Datatype> datatype_ = new HashMap<>();
    private List<Datatype> datatype_ordering_ = new ArrayList<>();
    private List<String> datatype_names_ = new ArrayList<>();

    private Map<String,Operator> operator_ = new HashMap<>();
    private List<Operator> operator_ordering_ = new ArrayList<>();
    private List<String> operator_names_ = new ArrayList<>();

    private Map<String,AxiomaticDefinition> axiom_def_ = new HashMap<>();
    private List<AxiomaticDefinition> axiom_def_ordering_ = new ArrayList<>();
    private List<String> axiom_def_names_ = new ArrayList<>();

    private Map<String,Theorem> theorems_ = new HashMap<>();
    private List<Theorem> theorem_ordering_ = new ArrayList<>();
    private List<String> theorem_names_ = new ArrayList<>();

    // private Map<String,ProofRules> proof_rules_ = new HashMap<>(); // TODO Create ProofRules class
    // private List<ProofRules> proof_rules_ordering_ = new ArrayList<>();
    // private List<String> proof_rules_names_ = new ArrayList<>();

    String comment_;

    File source_;
    Sys sys_;

    public Theory(String d, String n, Sys s, File f) // TODO Finish
    {
        directory_ = d;
        name_ = n;

        // types_ = new HashMap<>();
        // type_names_ = new ArrayList<>();
        sys_ = s;
        source_ = f;
    }

    public SymbolTable symbolTable()
    {
        return symbol_table_;
    }

    public Sys sys()
    {
        return sys_;
    }

    public String info()
    {
        String s = "thy: "+Util.padRightToLen(name_, ' ', 30);
        return s;
    }

    public String localName()
    {
        return name_;
    }

    /** The full name of the theory (directory/theory) */
    public String name()
    {
        return dir()+'/'+name_;
    }

    public String dir()
    {
        return directory_;
    }

    @Override
    public String toString()
    {
        return name_;
    }

    public String comment()
    {
        return comment_;
    }

    public boolean hasComment()
    {
        return !comment_.equals("");
    }

// -----------------------------------------------------------------------------
//    IMPORTS
// -----------------------------------------------------------------------------
    public Theory getImports(String name)
    {
        return imports_.get(name);
    }

    public List<Theory> importsOrdering()
    {
        return imports_ordering_;
    }

    public boolean hasImports()
    {
        return imports_.size() > 0;
    }

    public List<String> importsNames()
    {
        return imports_names_;
    }

    public void addImport(Theory imp)
    {
        imports_.put(imp.name(), imp); // This is the full name of the imported theory, including the directory
        imports_ordering_.add(imp);
        imports_names_ = imports_.keySet().stream().sorted().collect(Collectors.toList());
    }

// -----------------------------------------------------------------------------
//    TYPE PARAMETERS
// -----------------------------------------------------------------------------
    public TypeParameters getTypeParameters(String name)
    {
        return type_parameters_.get(name);
    }

    public List<TypeParameters> typeParametersOrdering()
    {
        return type_parameters_ordering_;
    }

    public boolean hasTypeParameters()
    {
        return type_parameters_.size() > 0;
    }

    public List<String> typeParametersNames()
    {
        return type_parameters_names_;
    }

    public void addTypeParameters(TypeParameters type_param)
    {
        type_parameters_.put(type_param.name(), type_param);
        type_parameters_ordering_.add(type_param);
        type_parameters_names_ = type_parameters_.keySet().stream().sorted().collect(Collectors.toList());
    }

// -----------------------------------------------------------------------------
//    DATATYPES
// -----------------------------------------------------------------------------
    public Datatype getDatatype(String name)
    {
        return datatype_.get(name);
    }

    public List<Datatype> datatypesOrdering()
    {
        return datatype_ordering_;
    }

    public boolean hasDatatype()
    {
        return datatype_.size() > 0;
    }

    public List<String> datatypeNames()
    {
        return datatype_names_;
    }

    public void addDatatype(Datatype datatype)
    {
        datatype_.put(datatype.name(), datatype);
        datatype_ordering_.add(datatype);
        datatype_names_ = datatype_.keySet().stream().sorted().collect(Collectors.toList());
    }

// -----------------------------------------------------------------------------
//    OPERATOR
// -----------------------------------------------------------------------------
    public Operator getOperator(String name)
    {
        return operator_.get(name);
    }

    public List<Operator> operatorOrdering()
    {
        return operator_ordering_;
    }

    public boolean hasOperator()
    {
        return operator_.size() > 0;
    }

    public List<String> operatorNames()
    {
        return operator_names_;
    }

    public void addOperator(Operator operator)
    {
        operator_.put(operator.name(), operator);
        operator_ordering_.add(operator);
        operator_names_ = operator_.keySet().stream().sorted().collect(Collectors.toList());
    }

// -----------------------------------------------------------------------------
//    AXIOMATIC DEFINITION
// -----------------------------------------------------------------------------
    public AxiomaticDefinition getAxiomaticDefinition(String name)
    {
        return axiom_def_.get(name);
    }

    public List<AxiomaticDefinition> axiomaticDefinitionOrdering()
    {
        return axiom_def_ordering_;
    }

    public boolean hasAxiomaticDefinition()
    {
        return axiom_def_.size() > 0;
    }

    public List<String> axiomaticDefinitionNames()
    {
        return axiom_def_names_;
    }

    public void addAxiomaticDefinition(AxiomaticDefinition axiom_def)
    {
        axiom_def_.put(axiom_def.name(), axiom_def);
        axiom_def_ordering_.add(axiom_def);
        axiom_def_names_ = axiom_def_.keySet().stream().sorted().collect(Collectors.toList());
    }

// -----------------------------------------------------------------------------
//    THEOREMS
// -----------------------------------------------------------------------------

    public Theorem getTheorem(String name)
    {
        return theorems_.get(name);
    }

    public List<Theorem> theoremOrdering()
    {
        return theorem_ordering_;
    }

    public boolean hasTheorems()
    {
        return theorem_ordering_.size() > 0;
    }
    
    public List<String> theoremNames()
    {
        return theorem_names_;
    }

    public void addTheorem(Theorem t)
    {
        theorems_.put(t.name(), t);
        theorem_ordering_.add(t);
        theorem_names_ = theorems_.keySet().stream().sorted().collect(Collectors.toList());
    }

// -----------------------------------------------------------------------------
//    LOAD
// -----------------------------------------------------------------------------
    public void load() throws Exception
    {
        SAXReader reader = new SAXReader();
        Document document = reader.read(source_);
        log.debug("loading theory "+source_);

        List<Node> list = document.selectNodes("//org.eventb.theory.core.theoryRoot");

        for (Node m : list)
        {
            comment_ = m.valueOf("@org.eventb.core.comment");
        }

        list = document.selectNodes("//org.eventb.theory.core.importTheory");
        for (Node i : list)
        {
            String t = i.valueOf("@org.eventb.theory.core.importTheory");
            
            // Get the name of the theory
            int endPos = t.indexOf(".dtf|");
            int beginPos = t.substring(0, endPos).lastIndexOf('/')+1;
            String theoryName = t.substring(beginPos, endPos);

            String theoryDir = t.substring(0, beginPos-1);
            int dirPos = theoryDir.lastIndexOf('/')+1;
            theoryDir = theoryDir.substring(dirPos);

            Theory importTh;
            if ((importTh = sys_.getTheory(theoryDir, theoryName)) == null)
            {
                String workspacePath = this.source_.getParentFile().getParent(); // Path of the workspace directory relative to the current directory
                File importFile = new File(workspacePath+'/'+theoryDir, theoryName+".tuf"); // The file theoryName.tuf in the directory workspacePath/theoryDir
                // TODO: make a "suffix" variable to theory, machine, context and use that instead of writing it in sys and here ? (.tuf above)
                if (!importFile.exists()) log.error("Error while loading theory %s, could not find imported theory %s", name(), importFile.getName());
                importTh = new Theory(theoryDir, theoryName, sys_, importFile);
                sys_.addTheory(importTh);
            }
            addImport(importTh);
        }

        list = document.selectNodes("//org.eventb.theory.core.typeParameter");
        for (Node tp : list)
        {
            String name = tp.valueOf("@org.eventb.core.identifier");
            String comment = tp.valueOf("@org.eventb.core.comment");
            addTypeParameters(new TypeParameters(name, comment));
        }

        list = document.selectNodes("//org.eventb.theory.core.datatypeDefinition");
        for (Node dt : list)
        {
            String name = dt.valueOf("@org.eventb.core.identifier");
            String comment = dt.valueOf("@org.eventb.core.comment");
            Datatype datatype = new Datatype(name, comment);

            List<Node> type_args = dt.selectNodes("org.eventb.theory.core.typeArgument");
            for (Node type : type_args)
            {
                String n = type.valueOf("@org.eventb.theory.core.givenType");

                datatype.addTypeArgument(getTypeParameters(n));
            }

            List<Node> cons_node = dt.selectNodes("org.eventb.theory.core.datatypeConstructor");
            for (Node cons : cons_node)
            {
                String n = cons.valueOf("@org.eventb.core.identifier");
                String c = cons.valueOf("@org.eventb.core.comment");

                Operator constructor = new Operator(n, c);

                List<Node> dests = cons.selectNodes("org.eventb.theory.core.constructorArgument");
                for (Node dest : dests)
                {
                    String nn = dest.valueOf("@org.eventb.core.identifier");
                    String cc = dest.valueOf("@org.eventb.core.comment");
                    String tt = dest.valueOf("@org.eventb.theory.core.type");

                    constructor.addArgument(new Arguments(nn, tt, cc));
                }

                datatype.addConstructor(constructor);
            }

            addDatatype(datatype);
        }

        list = document.selectNodes("//org.eventb.theory.core.newOperatorDefinition");
        for (Node op : list)
        {
            String name = op.valueOf("@org.eventb.core.label");
            String comment = op.valueOf("@org.eventb.core.comment");
            boolean associative = op.valueOf("@org.eventb.theory.core.associative").equals("true");
            boolean commutative = op.valueOf("@org.eventb.theory.core.commutative").equals("true");
            boolean infix = op.valueOf("@org.eventb.theory.core.notationType").equals("INFIX");
            boolean predicate = op.valueOf("@org.eventb.theory.core.formulaType").equals("false");

            Operator operator = new Operator(name,associative,commutative,comment, infix, predicate);

            List<Node> arguments = op.selectNodes("org.eventb.theory.core.operatorArgument");
            for (Node arg : arguments)
            {
                String i = arg.valueOf("@org.eventb.core.identifier");
                String e = arg.valueOf("@org.eventb.core.expression");
                String c = arg.valueOf("@org.eventb.core.comment");

                operator.addArgument(new Arguments(i, e, c)); 
            }

            List<Node> well_def_cond = op.selectNodes("org.eventb.theory.core.operatorWDcondition");
            for (Node wdc : well_def_cond)
            {
                /* the predicate that defines the wd condition */
                String well_def = wdc.valueOf("@org.eventb.core.predicate");
                String c = wdc.valueOf("@org.eventb.core.comment");
                
                operator.addWDC(new WDConditions(well_def, c));
            }

            /* Direct definitions */
            List<Node> direct_def = op.selectNodes("org.eventb.theory.core.directOperatorDefinition");
            for (Node dd : direct_def)
            {
                String i = operator.name()+"_direct_def";
                String d = dd.valueOf("@org.eventb.theory.core.formula");
                String c = dd.valueOf("@org.eventb.core.comment");

                operator.setDirectDef(new IsAFormula(i,d,c));
            }

            /* Recursive definitions */
            List<Node> recursive_args = op.selectNodes("org.eventb.theory.core.recursiveOperatorDefinition");
            for (Node ra : recursive_args)
            {
                String arg = ra.valueOf("@org.eventb.theory.core.inductiveArgument");
                String c = ra.valueOf("@org.eventb.core.comment");
                operator.setRecursiveCaseComment(arg, c);

                List<Node> recursive_defs = ra.selectNodes("org.eventb.theory.core.recursiveDefinitionCase");
                for (Node rd : recursive_defs)
                {
                    String n = operator.name()+"_recursive_case";
                    String nn = operator.name()+"_recursive_def";
                    String cc = rd.valueOf("@org.eventb.core.comment");
                    String e = rd.valueOf("@org.eventb.core.expression");
                    String def = rd.valueOf("@org.eventb.theory.core.formula");

                    operator.setRecursiveDef(arg, new IsAFormula(n, e, ""), new IsAFormula(nn, def, cc));
                }
            }

            addOperator(operator);
        }

        list = document.selectNodes("//org.eventb.theory.core.axiomaticDefinitionsBlock");
        for (Node axd : list)
        {
            String name = axd.valueOf("@org.eventb.core.label");
            String comment = axd.valueOf("@org.eventb.core.comment");
            AxiomaticDefinition axiomatic_definition = new AxiomaticDefinition(name,comment);

            List<Node> axiomatic_type_def = axd.selectNodes("org.eventb.theory.core.axiomaticTypeDefinition");
            for (Node ax_td : axiomatic_type_def)
            {
                String l = ax_td.valueOf("@org.eventb.core.identifier");
                String c = ax_td.valueOf("@org.eventb.core.comment");
                axiomatic_definition.addTypeDef(new TypeDef(l, c));
            }

            List<Node> axiomatic_op_def = axd.selectNodes("org.eventb.theory.core.axiomaticOperatorDefinition");
            for (Node ax_op : axiomatic_op_def)
            {
                String l = ax_op.valueOf("@org.eventb.core.label");
                String c = ax_op.valueOf("@org.eventb.core.comment");
                boolean associative = ax_op.valueOf("@org.eventb.theory.core.associative").equals("true");
                boolean commutative = ax_op.valueOf("@org.eventb.theory.core.commutative").equals("true");
                boolean infix = ax_op.valueOf("@org.eventb.theory.core.notationType").equals("INFIX");
                boolean predicate = ax_op.valueOf("@org.eventb.theory.core.formulaType").equals("false");

                Operator operator = new Operator(l, associative, commutative, c, infix, predicate);

                if (! predicate) {
                    String t = ax_op.valueOf("@org.eventb.theory.core.type");
                    operator.setReturnType(t);
                }

                List<Node> arguments = ax_op.selectNodes("org.eventb.theory.core.operatorArgument");
                for (Node arg : arguments)
                {
                    String ii = arg.valueOf("@org.eventb.core.identifier");
                    String ee = arg.valueOf("@org.eventb.core.expression");
                    String cc = arg.valueOf("@org.eventb.core.comment");

                    operator.addArgument(new Arguments(ii, ee, cc)); 
                }

                List<Node> well_def_cond = ax_op.selectNodes("org.eventb.theory.core.operatorWDcondition");
                for (Node wdc : well_def_cond)
                {
                    /* the predicate that defines the wd condition */
                    String well_def = wdc.valueOf("@org.eventb.core.predicate");
                    String cc = wdc.valueOf("@org.eventb.core.comment");
                    
                    operator.addWDC(new WDConditions(well_def, cc));
                }

                axiomatic_definition.addOperator(operator);
            }

            List<Node> axiom = axd.selectNodes("org.eventb.theory.core.axiomaticDefinitionAxiom");
            for (Node ax : axiom)
            {
                String l = ax.valueOf("@org.eventb.core.label");
                String p = ax.valueOf("@org.eventb.core.predicate");
                String c = ax.valueOf("@org.eventb.core.comment");
                axiomatic_definition.addAxiom(new Axiom(l,p,c,false));
            }

            addAxiomaticDefinition(axiomatic_definition);
        }

        list = document.selectNodes("//org.eventb.theory.core.theorem");
        for (Node n : list)
        {
            String name = n.valueOf("@org.eventb.core.label");
            String pred = n.valueOf("@org.eventb.core.predicate");
            String comment = n.valueOf("@org.eventb.core.comment");

            Theorem t = new Theorem(name, pred, comment);
            addTheorem(t);
        }
    }

// -----------------------------------------------------------------------------
//    buildSymbolTable
// -----------------------------------------------------------------------------
    private void buildSymbolTable()
    {
        if (symbol_table_ != null) return;

        symbol_table_ = sys_.newSymbolTable(name_);

        if ( hasImports() )
        {
            for (Theory i : importsOrdering()) {
                i.buildSymbolTable();
                symbol_table_.addParent(i.symbolTable());
            }
        }

        for (TypeParameters tp : typeParametersOrdering())
        {
            log.debug("added type parameter set %s to symbol table %s", tp.name(), symbol_table_.name());
            symbol_table_.addTypeParameter(tp);
        }

        for (Datatype dt : datatypesOrdering())
        {
            symbol_table_.addDatatype(dt);
            
            // There is no need to add type arguments since they have to be already defined type parameters

            for (Operator cst : dt.constructorsOrdering())
            {
                symbol_table_.addConstructor(cst);
                symbol_table_.addDestructorSymbols(cst.argumentsNames()); // destructors
            }
        }

        for (Operator op : operatorOrdering())
        {
            symbol_table_.addOperator(op);
        }

        for (AxiomaticDefinition axd : axiomaticDefinitionOrdering())
        {
            for (TypeDef axd_td : axd.typeDefOrdering())
            {
                symbol_table_.addTypedef(axd_td);
            }

            for (Operator axd_op : axd.operatorOrdering())
            {
                symbol_table_.addOperator(axd_op);
            }
        }

    }

// -----------------------------------------------------------------------------
//    PARSE
// -----------------------------------------------------------------------------
    public void parse()
    {
        buildSymbolTable();
        log.debug("parsing %s", name());

        for (TypeParameters tp : typeParametersOrdering())
        {
            Formula f = FormulaFactory.newSetSymbol(tp.name(),null);
            ImplType type = sys().typing().lookupImplType(f);
            log.debug("adding Type Parameter set type: "+type.name());
        }

        for (Datatype dt : datatypesOrdering())
        {
            dt.parse(symbol_table_);
        }

        for (AxiomaticDefinition axd : axiomaticDefinitionOrdering())
        {
            axd.parse(symbol_table_);
        }

        for (Operator op : operatorOrdering())
        {
            op.parse(symbol_table_);
        }

        for (Theorem t : theoremOrdering())
        {
            t.parse(symbol_table_);
            sys().typing().extractInfoFromAxiom(t.formula(), symbol_table_);
        }
    }
}
