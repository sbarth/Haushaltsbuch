package de.sb.plugin.finance.ui.views.statistics;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.VerticalAlignment;

public class AreaChart {
	private final JFreeChart chart;

	public AreaChart(final CategoryDataset dataset) {
		chart = ChartFactory.createAreaChart("Area Chart", "Category", "Value", dataset, PlotOrientation.VERTICAL, false, false, false);

		chart.setBackgroundPaint(Color.white);
		final TextTitle subtitle = new TextTitle("subtitle");
		subtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
		subtitle.setPosition(RectangleEdge.TOP);
		subtitle.setVerticalAlignment(VerticalAlignment.BOTTOM);
		chart.addSubtitle(subtitle);

		final CategoryPlot plot = chart.getCategoryPlot();
		plot.setForegroundAlpha(0.5f);

		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.white);

		final CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		domainAxis.setLowerMargin(0.0);
		domainAxis.setUpperMargin(0.0);
		domainAxis.addCategoryLabelToolTip("Type 1", "The first type.");
		domainAxis.addCategoryLabelToolTip("Type 2", "The second type.");

		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setLabelAngle(0 * Math.PI / 2.0);
	}

	public JFreeChart getChart() {
		return chart;
	}
}
