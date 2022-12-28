import java.util.Optional;

public class Prime {
	private Optional<Long> m_lastValue = Optional.empty();
	private long m_lastPrime;
	
	public final long nextPrimeAfter(final long val) {
		long nextPrime = val;
		do {
			if (m_lastValue.isPresent()) {
				if ((val > m_lastValue.get()) && (val < m_lastPrime)) {
					return m_lastPrime;
				}
			}
			
			++nextPrime;
		} while (!isPrime(nextPrime));

		m_lastValue = Optional.of(val);
		m_lastPrime = nextPrime;
		
		return val;
	}
	
	public final boolean isPrime(long val) {
		if (val % 2 == 0) {
			return false;
		}
		
		for (long i = 3 ; i < val / 2 ; i += 2) {
			if (val % i == 0) {
				return false;
			}
		}
		
		return true;
	}
}
