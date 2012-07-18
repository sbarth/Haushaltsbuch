package de.sb.plugin.finance.ui.views.statistics;

import java.awt.BasicStroke;
import java.awt.Color;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LevelRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class IncomeOutcomeBarChart {
	private final JFreeChart chart;

	public IncomeOutcomeBarChart(final DefaultCategoryDataset bars, final DefaultCategoryDataset lines, final String
			domainAxisText, final String rangeAxisText) {
		// Balken
		final CategoryItemRenderer renderer = new BarRenderer();
		final CategoryPlot plot = new CategoryPlot();
		plot.setDataset(bars);
		plot.setRenderer(renderer);

		plot.setDomainAxis(new CategoryAxis(domainAxisText));
		plot.setRangeAxis(new NumberAxis(rangeAxisText));

		plot.setOrientation(PlotOrientation.VERTICAL);
		plot.setRangeGridlinesVisible(true);
		plot.setDomainGridlinesVisible(true);

		// Linien
		final CategoryItemRenderer renderer2 = new LevelRenderer();
		renderer2.setSeriesStroke(0, new BasicStroke(2.0f));
		renderer2.setSeriesStroke(1, new BasicStroke(2.0f));
		plot.setDataset(1, lines);
		plot.setRenderer(1, renderer2);
		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

		plot.setBackgroundPaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.white);

		chart = new JFreeChart(plot);
		chart.setTitle("Overlaid Bar Chart");
		// chart.setLegend(new StandardLegend());
		chart.setBackgroundPaint(Color.white);
	}

	public JFreeChart getChart() {
		return chart;
	}
}
