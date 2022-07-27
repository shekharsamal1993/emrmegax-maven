//package com.appliedinformatics.cdaapi.mu2parser;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import org.eclipse.emf.common.util.Diagnostic;
//import org.eclipse.emf.ecore.EReference;
//import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.BasicFeatureMapEntry;
//import org.eclipse.emf.ecore.util.FeatureMap;
//import org.eclipse.emf.ecore.xml.type.AnyType;
//import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
//import org.openhealthtools.mdht.uml.cda.Section;
//import org.openhealthtools.mdht.uml.cda.ccd.CCDPackage;
//import org.openhealthtools.mdht.uml.cda.ccd.ContinuityOfCareDocument;
//import org.openhealthtools.mdht.uml.cda.consol.AllergiesSection;
//import org.openhealthtools.mdht.uml.cda.consol.MedicationsSection;
//import org.openhealthtools.mdht.uml.cda.consol.ProblemSection;
//import org.openhealthtools.mdht.uml.cda.consol.ProceduresSection;
//import org.openhealthtools.mdht.uml.cda.consol.ResultsSection;
//import org.openhealthtools.mdht.uml.cda.hitsp.DiagnosticResultsSection;
//import org.openhealthtools.mdht.uml.cda.hitsp.PatientSummary;
//import org.openhealthtools.mdht.uml.cda.mu2consol.ClinicalOfficeVisitSummary;
//import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
//import org.openhealthtools.mdht.uml.cda.util.ValidationResult;
//
///**
// *
// * @author Chintan Patel <chintan@trialx.com>
// *
// * CDAParser - The core CDAParser that parses an input stream to parse a
// * HITSP-32 or HITSP-83 document
// *
// */
//public class CDAParser {
//
//    //Initialize the document variables
//    ClinicalDocument cd = null;
//    MedicationsSection medicationsSection = null;
//    ProblemSection problemSection = null;
//    AllergiesSection allergySection = null;
//    ResultsSection resultsSection = null;
//    DiagnosticResultsSection diagnosticResultsSection = null;
//    ProceduresSection proceduresSection = null;
//    PatientSummary ps = null; //for HITSP 83
//    ContinuityOfCareDocument ccd = null; //for HITSP 32
//    Section VitalSection = null;
//    public ClinicalOfficeVisitSummary covs = null; //for Mu2 C-CDA
//
//    /**
//     * Constructor for the CDA Parser. Accepts an InputStream of a CDA document
//     * in either HITSP 32 (CCD) or HITSP 83 (CCDA) document
//     *
//     * @param cda_input
//     */
//    public CDAParser(InputStream cda_input) {
////		HITSPPackage.eINSTANCE.eClass();
//        //ContinuityOfCareDocument doc = CCDFactory.eINSTANCE.createContinuityOfCareDocument().init();
//
//        //Mu2consolPackage.eINSTANCE.eClass();
//        ValidationResult result = new ValidationResult();
//
//        try {
//            //cd = CDAUtil.load(cda_input, result); //Load CDA document and validate
//
//            cd = CDAUtil.loadAs(
//                    cda_input,
//                    CCDPackage.eINSTANCE.getContinuityOfCareDocument(),
//                    result);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        //Output diagnostic error messages
//        //TODO: return any errors to the client
//        for (Diagnostic diagnostic : result.getErrorDiagnostics()) {
//            //System.out.println("ERROR: " + diagnostic.getMessage());
//        }
//        for (Diagnostic diagnostic : result.getWarningDiagnostics()) {
//            //System.out.println("WARNING: " + diagnostic.getMessage());
//        }
//        ContinuityOfCareDocument covs = (ContinuityOfCareDocument) cd;
//        // covs = (ClinicalOfficeVisitSummary)cd;
//        System.out.println("NOT A CCDA or PATIENT SUMMARY!");
//        //System.out.println("first "+cd);
//        //System.out.println("second "+covs);
//        for (Section sec : covs.getAllSections()) {
//            System.out.println("title=" + sec.getTitle().getText());
//            System.out.println("text=" + traverse(sec.getText().getMixed()));
//        }
//
////		medicationsSection 	= covs.getMedicationsSection();
////		problemSection = covs.getProblemSection();
////		allergySection = covs.getAllergiesSection();
////		resultsSection = covs.getResultsSection();
////		proceduresSection = covs.getProceduresSection();
//    }
//
//    /**
//     * Get Medications from the CDA
//     *
//     * @return ArrayList of parsed medications
//     */
//    public ArrayList getMedications() {
//        return (new MedicationParser(medicationsSection)).parse();
//    }
//
//    /**
//     * Get Allergies from the CDA
//     *
//     * @return ArrayList of parsed allergies
//     */
//    public ArrayList getAllergies() {
//        return (new AllergyParser(allergySection)).parse();
//    }
//
//    /**
//     * Get results section from the CDA
//     *
//     * @return ArrayList of parsed results
//     */
//    public ArrayList getResults() {
//        //Decide between HITSP 32 versus 83
//        if (ps != null) {
//            return (new ResultParser(diagnosticResultsSection)).parse();
//        } else {
//            return (new ResultParser(resultsSection)).parse();
//        }
//    }
//
//    /**
//     * Get problems from the CDA
//     *
//     * @return ArrayList of parsed problems
//     */
//    public ArrayList getProblems() {
//        return (new ProblemParser(problemSection)).parse();
//    }
//
//    public ArrayList getVitals() {
//        return (new VitalSignParser(VitalSection)).parse();
//    }
//
//    public ArrayList getProcedures() {
//        return (new ProcedureParser(proceduresSection)).parse();
//    }
//
//    /**
//     * Get demographics from the CDA
//     *
//     * @return HashMap/Dictionary of Demographics
//     */
//    public HashMap getDemographics() {
//        return (new DemographicParser(cd)).parse();
//    }
//
//    public static void main(String[] args) {
//        InputStream is;
//        try {
//            is = new FileInputStream(new File("C:/ccda-rest-api-master/ccda-rest-api-master/ccd_samples/CCD-File.xml"));
//            CDAParser cdaParser = new CDAParser(is);
//            System.out.println("\n\n******* MEDICATIONS ************\n\n");
//            System.out.println(cdaParser.getMedications());
//
//            System.out.println(("\n\n****** RESULTS ***************\n\n"));
//            System.out.println(cdaParser.getResults());
//
//            System.out.println(("\n\n****** ALLERGIES ***************\n\n"));
//            System.out.println(cdaParser.getAllergies());
//
//            System.out.println(("\n\n****** PROBLEMS ***************\n\n"));
//            System.out.println(cdaParser.getProblems());
//
//            System.out.println(("\n\n****** DEMOGRAPHICS ***************\n\n"));
//            System.out.println(cdaParser.getDemographics());
//
//            System.out.println(("\n\n****** VITALS ***************\n\n"));
//            System.out.println(cdaParser.getVitals());
//
//            System.out.println(("\n\n****** PROCEDURES ***************\n\n"));
//            System.out.println(cdaParser.getProcedures());
//
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    private static String traverse(FeatureMap featureMap) {
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i <= featureMap.size() - 1; i++) {
//            org.eclipse.emf.ecore.util.FeatureMap.Entry entry = (org.eclipse.emf.ecore.util.FeatureMap.Entry) featureMap.get(i);
//            if (((BasicFeatureMapEntry) entry).getEStructuralFeature() instanceof EReference) {
//                String tagName = ((BasicFeatureMapEntry) entry).getEStructuralFeature().getName();
//                builder.append("<").
//                        append(tagName);
//                AnyType anyType = (AnyType) ((org.eclipse.emf.ecore.util.FeatureMap.Entry) entry).getValue();
//                builder.append(traverseAttributes(anyType.getAnyAttribute()));
//                builder.append(">");
//                builder.append(traverse(anyType.getMixed()));
//                builder.append("</").append(tagName).append(">");
//
//            } else {
//                if (((org.eclipse.emf.ecore.util.FeatureMap.Entry) entry).getValue() != null) {
//                    String value = ((org.eclipse.emf.ecore.util.FeatureMap.Entry) entry).getValue().toString();
//                    if (value.trim().length() > 0) {
//                        builder.append(value);
//                    }
//                } else {
//                    builder.append(">");
//                }
//            }
//
//        }
//
//        return builder.toString();
//    }
//
//    private static String traverseAttributes(FeatureMap anyAttribute) {
//        StringBuilder builder = new StringBuilder();
//        for (org.eclipse.emf.ecore.util.FeatureMap.Entry entry : anyAttribute) {
//            builder.append(" ").append(entry.getEStructuralFeature().getName()).append("='").append(entry.getValue().
//                    toString()).append("'");
//        }
//
//        return builder.toString();
//    }
//
//}
