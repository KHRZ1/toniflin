---

title: 'ToniFlin: An Android application for isotonic adjustment of parenteral formulations using cryoscopic method equations'

tags:

&nbsp; - Android

&nbsp; - pharmaceutics

&nbsp; - tonicity

&nbsp; - parenteral formulation

&nbsp; - open source

authors:

&nbsp; - name: Abd. Kakhar Umar

&nbsp;   orcid: 0000-0002-0667-9566

&nbsp;   corresponding: true

&nbsp;   affiliation: "1"

&nbsp; - name: James David M. Tuñacao

&nbsp;   affiliation: "1, 2"

affiliations:

&nbsp;- name: Medical Informatics Laboratory, ETFLIN, Palu 94225, Indonesia

&nbsp;  index: 1

&nbsp;- name: Department of Physics, School of Arts and Sciences, University of San Carlos, Cebu 6000, Philippines

&nbsp;  index: 2

date: 17 April 2023

bibliography: paper.bib

---



\# Summary



ToniFlin is an open-source Android application designed to adjust ingredient levels in parenteral formulations to achieve isotonicity. Built on equations derived from the cryoscopic method, it enables pharmacists and formulators to correct hypertonic or hypotonic solutions by recalculating excipient concentrations instead of relying solely on dilution. The app integrates a database of parenteral excipients with their freezing point depression (∆Tf) values and applies tailored algorithms to balance solution tonicity while maintaining ingredient functionality. ToniFlin provides a fast, accurate, and user-friendly solution for both educational and industrial formulation settings, promoting reliable and patient-safe isotonic preparations through accessible mobile technology.



\# Statement of need



Tonicity is a critical parameter in pharmaceutical formulations, particularly for parenteral products, as deviations from isotonic conditions can cause pain, irritation, or cellular damage (Goldman, 2017; Weiss \& Weiss, 1990). While existing methods—such as the White-Vincent dilution approach—can adjust hypotonic formulations, they provide limited solutions for hypertonic cases, often resulting in impractically large or unstable volumes (Travagli, 2018). Despite the clinical and educational importance of mastering isotonic adjustment, no accessible tool currently integrates theoretical equations with practical application for this purpose.



ToniFlin addresses this gap by providing an Android-based application that automatically performs isotonic adjustments through ingredient-level recalculations based on the cryoscopic method (Umar et al., 2019). It enables pharmacists, students, and researchers to efficiently design, analyze, and optimize isotonic formulations, eliminating manual computation errors and enhancing understanding of tonicity control in parenteral preparations.



\# Mathematical Basis for Isotonic Adjustment



The calculation method in ToniFlin is based on the cryoscopic principle, which relates the freezing point depression ($\\Delta T\_f$) of a solution to its solute concentration (Churakova et al., 2019; Li et al., 2017). For a mixture of ingredients, isotonicity is achieved when the total freezing point depression equals that of blood ($\\Delta T\_f = 0.52^\\circ \\mathrm{C}$ at 0.9% NaCl) (Kamat \& DeLuca, 2019; Savva, 2019). The isotonic condition is expressed as:



$$

\\sum\_i (\\Delta T\_{f,i} \\times C\_i) = 0.52

$$



where $\\Delta T\_{f,i}$ is the freezing point depression per 1% concentration of the i-th ingredient, and $C\_i$ is its concentration in percentage.



Each ingredient concentration is related to its mass ($m\_i$) and solution volume ($V$) by:



$$

C\_i = \\frac{m\_i}{V}

$$



Substituting Equation (2) into Equation (1) gives:



$$

\\sum\_i \\left(\\Delta T\_{f,i} \\times \\frac{m\_i}{V}\\right) = 0.52

$$



Solving for $V$ yields:



$$

V = \\frac{\\sum\_i (\\Delta T\_{f,i} \\times m\_i)}{0.52}

$$



The difference between the total volume ($V\_t$) and the isotonic volume ($V$) determines the non-isotonic portion of the formulation. To isotonicize this remaining fraction, the concentration of each adjustable ingredient is modified using:



$$

C\_{i,adj} = C\_{i,init} \\times \\frac{V\_t}{V}

$$



where $C\_{i,adj}$ and $C\_{i,init}$ represent the adjusted and initial concentrations, respectively. These equations were implemented algorithmically in ToniFlin to automatically correct hypertonic or hypotonic formulations while maintaining each ingredient’s functional range.



\# Major Features



* Automated isotonic adjustment: Calculates corrected ingredient levels using the implemented cryoscopic equations.
* Ingredient locking: Allows users to lock specific ingredients whose concentrations must remain constant.
* Excipients database: Includes $\\Delta T\_f$ values for over 400 parenteral ingredients from the Indonesian Pharmacopoeia.
* User-friendly interface: Consists of three panels — ingredient selection, concentration list, and tonicity status — with real-time feedback.
* Offline operation: All calculations are performed locally, requiring no internet connection.
* Cross-platform adaptability: Source code is modular and can be adapted for desktop or web-based implementations



\# References



