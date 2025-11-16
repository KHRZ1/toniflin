---
title: "ToniFlin: An Android application for isotonic adjustment of parenteral formulations using cryoscopic method equations"
tags:
  - Android
  - pharmaceutics
  - tonicity
  - parenteral formulation
  - open source
authors:
  - name: Abd. Kakhar Umar
    orcid: 0000-0002-0667-9566
    corresponding: true
    affiliation: "1"
  - name: James David M. Tuñacao
    affiliation: "1, 2"
affiliations:
  - name: Medical Informatics Laboratory, ETFLIN, Palu 94225, Indonesia
    index: 1
  - name: Department of Physics, School of Arts and Sciences, University of San Carlos, Cebu 6000, Philippines
    index: 2
date: 17 April 2023
bibliography: paper.bib
---

# Summary

ToniFlin is an open-source Android application designed to adjust ingredient levels in parenteral formulations to achieve isotonicity. Built on equations derived from the cryoscopic method, it enables pharmacists and formulators to correct hypertonic or hypotonic solutions by recalculating excipient concentrations instead of relying solely on dilution. The app integrates a database of parenteral excipients with their freezing point depression (∆Tf) values and applies tailored algorithms to balance solution tonicity while maintaining ingredient functionality. ToniFlin provides a fast, accurate, and user-friendly solution for both educational and industrial formulation settings, promoting reliable and patient-safe isotonic preparations through accessible mobile technology.

# Statement of need

Tonicity is a critical parameter in pharmaceutical formulations, particularly for parenteral products, as deviations from isotonic conditions can cause pain, irritation, or cellular damage (Goldman, 2017; Weiss \& Weiss, 1990). While existing methods—such as the White-Vincent dilution approach—can adjust hypotonic formulations, they provide limited solutions for hypertonic cases, often resulting in impractically large or unstable volumes (Travagli, 2018). Despite the clinical and educational importance of mastering isotonic adjustment, no accessible tool currently integrates theoretical equations with practical application for this purpose.

ToniFlin addresses this gap by providing an Android-based application that automatically performs isotonic adjustments through ingredient-level recalculations based on the cryoscopic method (Umar et al., 2019). It enables pharmacists, students, and researchers to efficiently design, analyze, and optimize isotonic formulations, eliminating manual computation errors and enhancing understanding of tonicity control in parenteral preparations.

# Mathematical Basis for Isotonic Adjustment

The calculation method in ToniFlin is based on the cryoscopic principle, which relates the freezing point depression ($\Delta T_f$) of a solution to its solute concentration (Churakova et al., 2019; Li et al., 2017). For a mixture of ingredients, isotonicity is achieved when the total freezing point depression equals that of blood ($ \Delta T_f = 0.52\:^{\circ}\mathrm{C} $ at 0.9% NaCl) (Kamat \& DeLuca, 2019; Savva, 2019). The isotonic condition is expressed as:

$$\begin{equation}\label{eq:fourier}
\sum_{n}\left[\Delta Tf_i \times C_i\right] &= 0.52
\end{equation}$$

where $\Delta T f_i$ is the freezing point depression per 1% concentration of the i-th ingredient, and $C_i$ is its isotonic concentration in percentage. Based on Eq. 1, we can calculate the volume of the formulation that has been made isotonic by the ingredients, $V_{iso}$ and the remaining volume that is not yet isotonic, $V_{niso}$. We first note that ingredient isotonic concentration $C_i$ is related to its isotonic mass $Q_i$ and volume $V_{iso}$ via the relation,

$$\begin{equation}\label{eq:fourier}
C_i &= Q_i \times \frac{100}{V_{\mathrm{iso}}}
\end{equation}$$

Plug Eq. 2 into Eq. 1 to find,

$$\begin{equation}\label{eq:fourier}
\frac{100}{V_{iso}}\sum_n\bigl[\Delta Tf_i \times Q_i\bigr]=0.52.
\end{equation}$$

Solving for $V_{iso}$ yields:

$$\begin{equation}\label{eq:fourier}
V_{iso} = 192 \sum_n \left[ \Delta Tf_i \times Q_i \right]
\end{equation}$$

and using Eq. 2 in Eq. 4 allows us to write $V_{iso}$ as

$$\begin{equation}\label{eq:fourier}
V_{iso} = 1.92V_0 \sum_{n}\left[ \Delta Tf_i \times C_i \right]
\end{equation}$$

where we designate $V_0$ as the initial total volume. $V_{niso}$ is then calculated as the difference between $V_0$ and $V_{iso}$. 

Furthermore, we can determine the respective amounts of each ingredient required to isotonicize $V_{niso}$ using the equation,

$$\begin{equation}\label{eq:fourier}
C_i \times V_i &= C_{0i} \times V_0,
\end{equation}$$

where $C_i$ is isotonic concentration, $C_{0i}$ is the initial concentration of the i-th ingredient.  Using Eq. 5, we can write Eq. 6 as

$$\begin{equation}\label{eq:fourier}
C_i &= \frac{C_{0i}}{1.92\displaystyle\sum_{n}\big[\Delta Tf_i\times C_i\big]}
\end{equation}$$

# Major Features

* Automated isotonic adjustment: Calculates corrected ingredient levels using the implemented cryoscopic equations.
* Ingredient locking: Allows users to lock specific ingredients whose concentrations must remain constant.
* Excipients database: Includes $\\Delta T\_f$ values for over 400 parenteral ingredients from the Indonesian Pharmacopoeia.
* User-friendly interface: Consists of three panels — ingredient selection, concentration list, and tonicity status — with real-time feedback.
* Offline operation: All calculations are performed locally, requiring no internet connection.
* Cross-platform adaptability: Source code is modular and can be adapted for desktop or web-based implementations

# References