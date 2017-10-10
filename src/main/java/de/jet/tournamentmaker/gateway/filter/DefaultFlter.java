package de.jet.tournamentmaker.gateway.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

@Component
public class DefaultFlter extends ZuulFilter
{
	@Override
	public Object run()
	{
		return null;
	}

	@Override
	public boolean shouldFilter()
	{
		return false;
	}

	@Override
	public int filterOrder()
	{
		return 0;
	}

	@Override
	public String filterType()
	{
		return null;
	}
}
