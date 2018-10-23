class MedianFinder {
    // maxHeap to keep the smaller half of the nums
    // minHeap to keep the larger half of the nums
    // if odd # of nums, maxHeap keep one more #
    
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
        
    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>();   
    }
    
    public void addNum(int num) {
        maxHeap.offer(num);
        // if new added # belongs to another half, then move to another heap
        minHeap.offer(maxHeap.poll()); 
        // if odd # of nums, maxHeap keep one more #
        if (maxHeap.size() < minHeap.size()) maxHeap.offer(minHeap.poll());
    }
    
    public double findMedian() {
        return maxHeap.size() > minHeap.size() ? 
            maxHeap.peek() : (maxHeap.peek() + minHeap.peek())/2.0;//不能写2，否则会错
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */