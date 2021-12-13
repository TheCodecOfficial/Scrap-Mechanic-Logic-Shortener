# Scrap-Mechanic-Logic-Shortener

This program loads a Scrap Mechanic blueprint and attempts to shorten logic circuits. Shortened circuit yield exactly the same result but use less gates and are therefore generally faster.

---

**Requirements**

The shortener will assume that your circuit is purely combinational and not sequential. This means that your gate network should be acyclic. Also using gates as delays to produce short signals is not allowed. If these requirements are not met, the shortened circuit might not function correctly.

